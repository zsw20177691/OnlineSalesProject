package com.Important.entity;

import com.Important.enums.UserType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 你就是下一个大佬
 */
@TableName("user")
@Data
@ApiModel(value = "user对象",description = "user表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7170449108888093231L;


    @ApiModelProperty("UUID+雪花算法")
    @TableId(value = "uuid",type = IdType.ASSIGN_UUID)
    private String  uuid;

    @ApiModelProperty("用户名")
    @TableField(value = "username")
    private String  username;

    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String  password;

    @ApiModelProperty("用户类型")
    @TableField(value = "user_type")
    private Integer userType;

    @ApiModelProperty("电话号码")
    @TableField(value = "telephone")
    private String telephone;

    @ApiModelProperty("头像url")
    @TableField(value = "url")
    private String url;

    @ApiModelProperty("用户账号状态")
    @TableField(value = "user_start")
    private Integer userStart;


}
