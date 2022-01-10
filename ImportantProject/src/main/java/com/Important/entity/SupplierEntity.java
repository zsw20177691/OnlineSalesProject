package com.Important.entity;

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

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Data
@ApiModel(value = "供货商基本信息")
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "supplier")
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 5616843620897465324L;

    @ApiModelProperty("供货商主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String  supplierId;

    @ApiModelProperty("用户主键")
    @TableField(value = "user_id")
    private String  userId;

    @ApiModelProperty("供应商地址")
    @TableField(value = "supplier_adds")
    private String  supplierAdds;

    @ApiModelProperty("供应商主真实姓名")
    @TableField(value = "name")
    private String  name;

    @ApiModelProperty("银行卡号")
    @TableField(value = "bank_card_no")
    private String  bankCardNo;

    @ApiModelProperty("统一信用代码")
    @TableField(value = "unified_credit_code")
    private String  unifiedCreditCode;

    @ApiModelProperty("产品类型 0：粮油米面 1：农副加工 2：新鲜水果 3：肉蛋鲜蔬 4：其他产品")
    @TableField(value = "product_type")
    private String  productType;

    @ApiModelProperty("供货商店名称")
    @TableField(value = "supplier_name")
    private String  supplierName;

    @ApiModelProperty("审核状态")
    @TableField(value = "audit_status")
    private Integer auditStatus;

    @ApiModelProperty("申请时间")
    @TableField(value = "time")
    private LocalDateTime  applicationTime;

    @ApiModelProperty("图片url")
    @TableField(value = "picture_url")
    private String  pictureUrl;

    @ApiModelProperty("视频url")
    @TableField(value = "video_url")
    private String  videoUrl;

}
