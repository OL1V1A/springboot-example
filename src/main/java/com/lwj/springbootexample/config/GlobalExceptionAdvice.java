package com.lwj.springbootexample.config;

import com.lwj.springbootexample.base.Result;
import com.lwj.springbootexample.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result<Void> handlerServiceException(HttpServletRequest request, ServiceException e) {
        return Result.create(e.getCode(), e.getMessage());
    }

    /** 普通异常处理 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result<Void> handleNormalException(HttpServletRequest request, Exception e) {
        log.error("System Exception - path={},params={}",request.getRequestURI(), request.getQueryString(), e);
        return Result.create(ServiceException.INTERNAL_SERVER_ERROR.getCode(), ServiceException.INTERNAL_SERVER_ERROR.getMessage());
    }
}
