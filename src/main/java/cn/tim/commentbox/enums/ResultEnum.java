package cn.tim.commentbox.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum implements CodeEnum {
    SUCCESS(200, "成功"),
    FAILED(201, "用户不存在"),
    COMMENT_TYPE_ERROR(1001, "评论类型错误（0、文章评论 1、子评论）"),
    ARTICLE_URL_NOT_EXIST(1002, "文章URL在数据库中不存在");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
