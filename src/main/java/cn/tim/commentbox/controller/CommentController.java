package cn.tim.commentbox.controller;

import cn.tim.commentbox.form.ChildCommentForm;
import cn.tim.commentbox.form.CommentForm;
import cn.tim.commentbox.form.PraiseForm;
import cn.tim.commentbox.service.CommentService;
import cn.tim.commentbox.utils.ResultVOUtil;
import cn.tim.commentbox.vo.ArticleCommentVO;
import cn.tim.commentbox.vo.CommentBoxVO;
import cn.tim.commentbox.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping
    public ResultVO newComment(CommentForm commentForm){
        commentService.insertArticleComment(commentForm);
        List<ArticleCommentVO> allCommentByArticle = commentService.getAllCommentByArticle(commentForm.getArticleUrl());
        return ResultVOUtil.success(allCommentByArticle);
    }

    @PostMapping("child")
    public ResultVO newChildComment(ChildCommentForm childCommentForm){
        commentService.insertChildComment(childCommentForm);
        List<ArticleCommentVO> allCommentByArticle = commentService.getAllCommentByArticle(childCommentForm.getArticleUrl());
        return ResultVOUtil.success(allCommentByArticle);
    }

    /**
     * 获取某文章下所有评论
     * @param articleUrl 文章URL
     * @return 评论列表
     */
    @GetMapping
    public ResultVO getAllComment(@RequestParam String articleUrl){
        CommentBoxVO commentBoxVO = new CommentBoxVO();
        List<ArticleCommentVO> allCommentByArticle = commentService.getAllCommentByArticle(articleUrl);
        commentBoxVO.setArticleComments(allCommentByArticle);
        return ResultVOUtil.success(commentBoxVO);
    }

    @PutMapping
    public ResultVO praiseComment(PraiseForm praiseForm){
        commentService.praiseForComment(praiseForm);
        List<ArticleCommentVO> allCommentByArticle = commentService.getAllCommentByArticle(praiseForm.getArticleUrl());
        return ResultVOUtil.success(allCommentByArticle);
    }
}

