package com.Important.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Data,@Builder两个注解合用会出现反序列化报错，原因找不到无参构造
 * 解决办法：
 * 类上加上 @AllArgsConstructor 与 @NoArgsConstructor 注解
 */
@Data
@Builder
@ApiModel(value = "用户审核记录表")
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "audit_record")
public class AuditRecordEntuty implements Serializable {

    private static final long serialVersionUID = 8179791205167934088L;

    @ApiModelProperty("记录表主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String  auditRecordId;

    @ApiModelProperty("审核时间")
    @TableField(value = "audit_time")
    private LocalDateTime auditTime;

    @ApiModelProperty("审核结果 0：失败 1：成功")
    @TableField(value = "audit_result")
    private Integer auditResult;

    @ApiModelProperty("审核备注")
    @TableField(value = "audit_mark")
    private String auditMark;

    @ApiModelProperty("供应商申请信息id")
    @TableField(value = "supplier_id")
    private String supplierId;

    @ApiModelProperty("用户申请成为供应商时间")
    @TableField(value = "supplier_time")
    private String supplierTime;


}
