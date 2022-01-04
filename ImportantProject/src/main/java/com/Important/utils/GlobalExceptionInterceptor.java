package com.Important.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * 参数异常拦截
 */
@Slf4j
@Component
public class GlobalExceptionInterceptor {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception e) {
        String failMsg = null;
        if (e instanceof MethodArgumentNotValidException)
        {
        // 拿到参数校验具体异常信息提示
            failMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        // 直接吐回给前端
        return failMsg;
    }

}
