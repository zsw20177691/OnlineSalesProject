package com.Important.vo;

import com.Important.enums.ResCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ResultVO<T> implements Serializable {
    private int code;
    private String  msg;
    private T data;

    public ResultVO<T> setCode(ResCode resCode) {
        this.code = resCode.code;
        return this;
    }

    public ResultVO<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultVO<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultVO<T> setData(T data) {
        this.data = data;
        return this;
    }

}
