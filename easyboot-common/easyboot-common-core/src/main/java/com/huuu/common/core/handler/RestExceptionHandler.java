package com.huuu.common.core.handler;

import com.huuu.base.result.Result;
import com.huuu.base.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 * @author chenzhenhu
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public Result<Void> authorizationException(AuthorizationException e) {
        log.error("权限错误：{}", e.getMessage(), e);
        return Result.error(ResultCode.UNAUTHORIZED);
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Result<Void> methodArgumentNotValidException(Exception e) {
        log.error("参数校验错误：{}", e.getMessage(), e);
        String message;
        if (e instanceof BindException) {
            message = ((BindException) e).getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        } else {
            message = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }

        return Result.error(ResultCode.PARAMETER_INVALID.getCode(), message);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Void> noHandlerFoundException(NoHandlerFoundException e) {
        log.error("地址不存在：{}", e.getMessage(), e);
        return Result.error(ResultCode.NO_HANDLER);
    }


    @ExceptionHandler(Exception.class)
    public Result<Void> exception(Exception e) {
        log.error("系统内部错误：{}", e.getMessage(), e);
        return Result.error(ResultCode.INTERNAL_EXCEPTION);
    }
}
