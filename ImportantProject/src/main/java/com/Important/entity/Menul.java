package com.Important.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@ApiModel(value = "菜单对象")
@TableName(value = "menul")
public class Menul implements Serializable {
    private static final long serialVersionUID = -1660119577352607201L;

    @ApiModelProperty(value = "菜单url")
    @TableField(value = "menu_url")
    private String  menuUrl;

    @ApiModelProperty(value = "菜单名称")
    @TableField(value = "menu_name")
    private String  menuName;

    @TableField(exist = false)
    private List<SecondaryMenu> secondaryMenus;
}
