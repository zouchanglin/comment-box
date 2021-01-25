package cn.tim.commentbox.exception;

import cn.tim.commentbox.enums.ResultEnum;
import lombok.Getter;

@Getter
public class CommentBoxException extends RuntimeException{
    private Integer code;

    public CommentBoxException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public CommentBoxException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
