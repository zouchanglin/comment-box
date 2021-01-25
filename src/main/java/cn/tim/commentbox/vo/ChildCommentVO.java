package cn.tim.commentbox.vo;

import lombok.Data;

@Data
public class ChildCommentVO {
    /**
     * Id
     */
    public int id;

    /**
     * ClientCode
     */
    public String clientCode;

    /**
     * ClientName
     */
    public String clientName;

    /**
     * 内容
     */
    public String commentContent;

    /**
     * 回复的ClientCode
     */
    public String replyClientCode;

    /**
     * 回复的ClientName
     */
    public String replyClientName;

    /**
     * 评论时间
     */
    public String commentTime;

    /**
     * 点赞数量
     */
    private int commentPraise;
}
