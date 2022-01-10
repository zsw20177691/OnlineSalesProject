package com.Important.utils;


import lombok.Getter;

/**
 * 系统运行中自定义异常情况抛出，由全局异常处理类捕获，并处理返回提示给用户
 */

@Getter
public class ExceptionResult extends RuntimeException {

    private int code;
    private String msg;

    public ExceptionResult(){
        this(503, "接口异常");
    }

    public ExceptionResult(String msg){
        this(503, msg);
    }

    public ExceptionResult(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}

