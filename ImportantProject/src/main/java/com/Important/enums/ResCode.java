package com.Important.enums;

/**
 * Description TODO 响应code枚举类
 * Author fanlei
 * Param
 * return 
 **/

public enum ResCode {
 
   // 成功
   SUCCESS(200),
 
   // 失败
   FAIL(500),
 
   // 未认证（签名错误）
   UNAUTHORIZED(401),
 
   // 接口不存在
   NOT_FOUND(404);
 
   // 服务器内部错误
//   INTERNAL_SERVER_ERROR(500);
 
   public int code;
 
   ResCode(int code) {
      this.code = code;
   }
}