package cn.tim.commentbox.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommentBoxVO {
    /**
     * 大小评论集合
     */
    private List<ArticleCommentVO> articleComments;

    /**
     * 绑定ClientCode（如果没传ClientCode时）
     */
    public String clientCode;

    /**
     * 绑定ClientCode（如果没传ClientCode时）
     */
    public String clientName;
}
