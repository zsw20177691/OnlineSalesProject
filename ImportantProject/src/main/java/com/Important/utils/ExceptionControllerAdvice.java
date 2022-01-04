package com.Important.utils;

import com.Important.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(ExceptionResult.class)
    public ResultVO<String> CustomizeExceptionHandler(ExceptionResult e) {
        log.error(e.getMsg(), e);
        return ResponseUtil.error(e.getMessage());
    }
}
