package com.Important.dto;

import com.Important.enums.UserType;
import com.google.common.base.Enums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.SecureRandom;

@Data
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = 6805547270807322822L;

    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码，不能为空")
    @NotEmpty(message = "验证码，不能空字符")
    private String verificationCode;


    @ApiModelProperty(value = "电话号码")
    @NotNull(message = "电话号码，不能为空")
    @NotEmpty(message = "电话号码，不能空字符")
    private String telephone;


    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名，不能为空")
    @NotEmpty(message = "用户名，不能空字符")
    private String  userName;


    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码，不能为空")
    @NotEmpty(message = "密码，不能空字符")
    private String  pawword;


    @ApiModelProperty(value = "头像url")
    @NotNull(message = "请选择头像")
    @NotEmpty(message = "请选择头像")
    private String  url;


    @ApiModelProperty(value = "用户类型")
    @NotNull(message = "请选择注册类型")
    private UserType   userType;

}
