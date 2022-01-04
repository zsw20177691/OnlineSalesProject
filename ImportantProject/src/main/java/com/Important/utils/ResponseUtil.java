package com.Important.utils;

import com.Important.enums.ResCode;
import com.Important.vo.ResultVO;


/**
 * Description TODO 封装的返回对象工具类
 * Author fanlei
 * Param
 * return
 **/
public class ResponseUtil {

   private final static String SUCCESS = "success";

   public static <T> ResultVO<T> ok() {
      return new ResultVO<T>().setCode(ResCode.SUCCESS).setMsg(SUCCESS);
   }

   public static <T> ResultVO<T> ok(T data) {
      return new ResultVO<T>().setCode(ResCode.SUCCESS).setMsg(SUCCESS).setData(data);
   }

   public static <T> ResultVO<T> error(String message) {
      return new ResultVO<T>().setCode(ResCode.FAIL).setMsg(message);
   }

   public static <T> ResultVO<T> rsp(int code, String msg) {
      return new ResultVO<T>().setCode(code).setMsg(msg);
   }

   public static <T> ResultVO<T> rsp(int code, String msg, T data) {
      return new ResultVO<T>().setCode(code).setMsg(msg).setData(data);
   }
}