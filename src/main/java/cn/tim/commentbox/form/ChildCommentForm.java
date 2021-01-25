package cn.tim.commentbox.form;

import lombok.Data;

@Data
public class ChildCommentForm {
    /**
     * 客户端识别码
     */
    public String clientId;

    /**
     * 回复客户端识别码
     */
    public String replyClientId;

    /**
     * 文章URL
     */
    public String articleUrl;

    /**
     * 内容
     */
    public String commentContent;

    /**
     * Email
     */
    public String email;

    /**
     * 父评论(是指文章类型的评论)
     */
    public int parentComment;
}
