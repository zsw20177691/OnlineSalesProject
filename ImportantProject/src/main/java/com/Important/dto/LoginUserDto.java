package com.Important.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginUserDto implements Serializable {
    private static final long serialVersionUID = -2372790069526151641L;
    @NotEmpty(message = "用户名不能为空字符串")
    @NotNull(message = "用户名不能为空")
    private String  telephone;

    @NotEmpty(message = "密码不能为空字符串")
    @NotNull(message = "密码不能为空")
    private String  password;
}
