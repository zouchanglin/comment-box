package cn.tim.commentbox.handler;

import cn.tim.commentbox.exception.CommentBoxException;
import cn.tim.commentbox.utils.ResultVOUtil;
import cn.tim.commentbox.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    /** 处理控制层 or 服务层异常 */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerMyException(CommentBoxException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
