package com.wg.base.backend.common.exception;

import com.wg.base.backend.common.result.Result;
import com.wg.base.backend.common.result.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof LogicException) {
            return Result.fail(((LogicException) e).getCode(),e.getMessage());
        } else {
            //对系统级异常进行日志记录
            LOGGER.error("系统异常:" + e.getMessage(), e);
        }
        return Result.fail(ResultMessage.SERVER_ERROR.getCode(),ResultMessage.SERVER_ERROR.getMsg());
    }
}
