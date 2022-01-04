package com.Important.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginUserDto {
    @NotEmpty(message = "用户名不能为空字符串")
    @NotNull(message = "用户名不能为空")
    private String  username;

    @NotEmpty(message = "密码不能为空字符串")
    @NotNull(message = "密码不能为空")
    private String  password;
}
