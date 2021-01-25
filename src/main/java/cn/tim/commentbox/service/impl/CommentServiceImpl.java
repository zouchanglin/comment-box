package cn.tim.commentbox.service.impl;

import cn.tim.commentbox.cache.PraiseLimitPool;
import cn.tim.commentbox.consts.CommentConsts;
import cn.tim.commentbox.entity.ArticleInfo;
import cn.tim.commentbox.entity.ChildComment;
import cn.tim.commentbox.entity.ClientInfo;
import cn.tim.commentbox.entity.ArticleComment;
import cn.tim.commentbox.enums.ResultEnum;
import cn.tim.commentbox.exception.CommentBoxException;
import cn.tim.commentbox.form.ChildCommentForm;
import cn.tim.commentbox.form.CommentForm;
import cn.tim.commentbox.form.PraiseForm;
import cn.tim.commentbox.repository.ArticleInfoRepository;
import cn.tim.commentbox.repository.ChildCommentRepository;
import cn.tim.commentbox.repository.ClientInfoRepository;
import cn.tim.commentbox.repository.ArticleCommentRepository;
import cn.tim.commentbox.service.ClientService;
import cn.tim.commentbox.service.CommentService;
import cn.tim.commentbox.service.SendMailService;
import cn.tim.commentbox.utils.ConstUtilPoll;
import cn.tim.commentbox.utils.KeyUtil;
import cn.tim.commentbox.vo.ArticleCommentVO;
import cn.tim.commentbox.vo.ChildCommentVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private ArticleCommentRepository articleCommentRepository;

    @Resource
    private ArticleInfoRepository articleInfoRepository;

    @Resource
    private ClientInfoRepository clientInfoRepository;

    @Resource
    private ChildCommentRepository childCommentRepository;

    @Resource
    private ClientService clientService;

    @Resource
    private SendMailService sendMailService;

    @Override
    public List<ArticleCommentVO> getAllCommentByArticle(String articleUrl) {
        ArticleInfo articleInfo = articleInfoRepository.findByArticleUrl(articleUrl);
        List<ArticleCommentVO> articleCommentVOList = null;
        if(articleInfo == null){
            // 数据没有就存储起来
            articleInfo = new ArticleInfo(KeyUtil.generateKey(), articleUrl, 0);
            articleInfoRepository.save(articleInfo);
        } else{
            // 该文章下全部的评论
            List<ArticleComment> commentsList = articleCommentRepository.findAllByArticleId(articleInfo.getArticleId());
            articleCommentVOList = Lists.newArrayListWithCapacity(commentsList.size());
            // 评论排个序
            ArticleComment[] articleCommentArray = new ArticleComment[commentsList.size()];
            commentsList.toArray(articleCommentArray);
            Arrays.sort(articleCommentArray);
            for(ArticleComment articleComment : articleCommentArray){
                articleCommentVOList.add(convertArticleComment2VO(articleComment));
            }
        }
        return articleCommentVOList;
    }

    @Override
    public void insertArticleComment(CommentForm commentForm) {
        ArticleComment articleComment = new ArticleComment();
        ArticleInfo articleInfo = articleInfoRepository.findFirstByArticleUrl(commentForm.getArticleUrl());
        if(articleInfo == null) {
            log.error("insertArticleComment articleInfo is NULL!!!");
            throw new CommentBoxException(ResultEnum.ARTICLE_URL_NOT_EXIST);
        }else {
            articleComment.setArticleId(articleInfo.getArticleId());
            articleComment.setCommentPraise(0);
            articleComment.setCommentClient(commentForm.getClientId());
            articleComment.setCreateTime(System.currentTimeMillis());
            articleComment.setCommentContent(commentForm.getCommentContent());
            articleComment.setGradeStar(commentForm.getGradeStar());
            articleComment.setCommentParent(0);
            String email = commentForm.getEmail();
            clientService.setClientEmail(commentForm.getClientId(), email);
            articleCommentRepository.save(articleComment);
        }
    }

    @Override
    public void insertChildComment(ChildCommentForm childCommentForm) {
        ChildComment childComment = new ChildComment();
        childComment.setReplyClient(childCommentForm.getReplyClientId());
        childComment.setCommentPraise(0);
        childComment.setCommentClient(childCommentForm.getClientId());
        childComment.setCreateTime(System.currentTimeMillis());
        childComment.setCommentContent(childCommentForm.getCommentContent());
        childComment.setCommentParent(childCommentForm.getParentComment());

        ChildComment save = childCommentRepository.save(childComment);
        log.info("insertChildComment save = {}", save);

        String replyClientId = childCommentForm.getReplyClientId();
        Optional<ClientInfo> byId = clientInfoRepository.findById(replyClientId);
        if(byId.isPresent()){
            ClientInfo clientInfo = byId.get();
            String clientEmail = clientInfo.getClientEmail();
            if(!StringUtils.isEmpty(clientEmail)){
                // 开始发送邮件
                String clientName = clientInfo.getClientName();
                HashMap<String, Object> map = new HashMap<>();
                map.put("userName", clientName);
                map.put("commentContent", childCommentForm.getCommentContent());
                map.put("hrefUrl", childCommentForm.getArticleUrl());
                sendMailService.sendTemplateMail(clientEmail, map, "评论收到新回复啦！@" + clientName, "index.ftl");
            }else {
                log.info("回复评论的目标客户端无Email.");
            }
        }else {
            log.error("insertChildComment 发送邮件目标客户端不存在！");
        }

    }

    @Override
    public void praiseForComment(PraiseForm praiseForm) {
        String clientId = praiseForm.getClientId();
        int commentId = praiseForm.getCommentId();
        int type = praiseForm.getType();
        if(CommentConsts.ARTICLE_COMMENT_TYPE.equals(type)){
            Optional<ArticleComment> articleComment = articleCommentRepository.findById(commentId);
            if(articleComment.isPresent()){
                ArticleComment comment = articleComment.get();
                if(PraiseLimitPool.find(clientId, commentId)){
                    comment.setCommentPraise(comment.getCommentPraise() + 1);
                    ArticleComment saveRet = articleCommentRepository.save(comment);
                    log.info("praiseForComment saveRet = {}", saveRet);
                    PraiseLimitPool.insertCache(clientId, commentId);
                }
            }
        }else if(CommentConsts.CHILD_COMMENT_TYPE.equals(type)){
            Optional<ChildComment> childComment = childCommentRepository.findById(commentId);
            if(childComment.isPresent()){
                if(PraiseLimitPool.findChild(clientId, commentId)) {
                    ChildComment comment = childComment.get();
                    comment.setCommentPraise(comment.getCommentPraise() + 1);
                    ChildComment saveRet = childCommentRepository.save(comment);
                    log.info("praiseForComment saveRet = {}", saveRet);
                    PraiseLimitPool.insertCacheChild(clientId, commentId);
                }
            }
        }else{
            throw new CommentBoxException(ResultEnum.COMMENT_TYPE_ERROR);
        }
    }

    private List<ChildCommentVO> convertChildComment2VO(ChildComment[] childComments) {
        List<ChildCommentVO> retList = Lists.newArrayListWithCapacity(childComments.length);
        ChildCommentVO childCommentVO;
        Optional<ClientInfo> clientOptional;
        Optional<ClientInfo> replyClientOptional;
        for(ChildComment childComment: childComments){
            clientOptional = clientInfoRepository.findById(childComment.getCommentClient());
            replyClientOptional = clientInfoRepository.findById(childComment.getReplyClient());
            childCommentVO = new ChildCommentVO();
            if(clientOptional.isPresent() && replyClientOptional.isPresent()){
                childCommentVO.setId(childComment.getCommentId());
                childCommentVO.setClientCode(clientOptional.get().getClientId());
                childCommentVO.setClientName(clientOptional.get().getClientName());
                childCommentVO.setReplyClientCode(replyClientOptional.get().getClientId());
                childCommentVO.setReplyClientName(replyClientOptional.get().getClientName());
                childCommentVO.setCommentContent(childComment.getCommentContent());
                childCommentVO.setCommentTime(ConstUtilPoll.dateFormat.format(new Date(childComment.getCreateTime())));
                childCommentVO.setCommentPraise(childComment.getCommentPraise());
                retList.add(childCommentVO);
            }
        }
        return retList;
    }

    private ArticleCommentVO convertArticleComment2VO(ArticleComment articleComment) {
        ArticleCommentVO retCommentVO = new ArticleCommentVO();
        String clientId = articleComment.getCommentClient();
        ClientInfo clientInfo;
        Optional<ClientInfo> clientInfoOptional = clientInfoRepository.findById(clientId);
        if(clientInfoOptional.isPresent()){
            retCommentVO = new ArticleCommentVO();
            retCommentVO.setId(articleComment.getCommentId());
            clientInfo = clientInfoOptional.get();
            retCommentVO.setClientCode(clientInfo.getClientId());
            retCommentVO.setClientName(clientInfo.getClientName());
            retCommentVO.setCommentTime(ConstUtilPoll.dateFormat.format(new Date(articleComment.getCreateTime())));
            retCommentVO.setAvatarUrl(clientInfo.getClientIcon());
            retCommentVO.setCommentPraise(articleComment.getCommentPraise());
            List<ChildComment> childComments = childCommentRepository.findAllByCommentParent(articleComment.getCommentId());
            // 子评论按照点赞排个序
            ChildComment[] childCommentArr = new ChildComment[childComments.size()];
            childComments.toArray(childCommentArr);
            Arrays.sort(childCommentArr);
            retCommentVO.setChildComments(convertChildComment2VO(childCommentArr));
            retCommentVO.setCommentContent(articleComment.getCommentContent());
        }
        return retCommentVO;
    }
}
