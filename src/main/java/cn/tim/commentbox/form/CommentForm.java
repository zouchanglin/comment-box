package cn.tim.commentbox.form;

import lombok.Data;

@Data
public class CommentForm {
    /**
     * 客户端识别码
     */
    public String clientId;
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
     * 文章评分
     */
    public int gradeStar;
}
