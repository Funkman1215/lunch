package com.funkman.lunch.handler;

import com.funkman.lunch.entity.Error;
import com.funkman.lunch.entity.Result;
import com.funkman.lunch.execption.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerHandler {

    @ResponseBody
    @ExceptionHandler(value = UserException.class)
    public Result errorHandler(Exception e) {
        if (e instanceof UserException) {
            return Result.error(new Error(100, "用户异常"), null);
        }
        return Result.error(new Error(200, "非用户异常"), null);
    }
}
