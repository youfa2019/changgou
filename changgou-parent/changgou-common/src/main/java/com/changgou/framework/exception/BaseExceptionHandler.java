package com.changgou.framework.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BaseExceptionHandler
 * @Description
 * @Author 传智播客
 * @Date 2019/8/1 22:46
 * @Version 1.0
 **/
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * @Author 传智播客
     * @Description 异常处理
     * @Date 2019/8/3 17:42
     * @Param [e]
     * @return entity.Result
     **/
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
