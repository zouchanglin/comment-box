package cn.tim.commentbox.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleCommentVO {
    /**
     * Id
     */
    private int id;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * ClientCode
     */
    private String clientCode;

    /**
     * ClientName
     */
    private String clientName;

    /**
     * 内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private String commentTime;

    /**
     * 点赞数
     */
    private int commentPraise;

    /**
     * 底下的子评论
     */
    private List<ChildCommentVO> childComments;
}
