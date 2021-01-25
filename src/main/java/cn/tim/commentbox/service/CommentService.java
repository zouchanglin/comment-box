package cn.tim.commentbox.service;

import cn.tim.commentbox.form.ChildCommentForm;
import cn.tim.commentbox.form.CommentForm;
import cn.tim.commentbox.form.PraiseForm;
import cn.tim.commentbox.vo.ArticleCommentVO;

import java.util.List;


public interface CommentService {
    /**
     * 获取一篇文章的所有评论
     * @param articleUrl 文章 Url
     * @return
     */
    List<ArticleCommentVO> getAllCommentByArticle(String articleUrl);

    void insertArticleComment(CommentForm commentForm);

    void praiseForComment(PraiseForm praiseForm);

    void insertChildComment(ChildCommentForm childCommentForm);
}
