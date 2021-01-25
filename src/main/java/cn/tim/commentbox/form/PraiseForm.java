package cn.tim.commentbox.form;

import lombok.Data;

@Data
public class PraiseForm {
    private String clientId;

    private int commentId;

    /**
     * 类型（0、文章评论 1、评论的评论）
     */
    private int type;

    /**
     * 文章URL
     */
    public String articleUrl;
}
