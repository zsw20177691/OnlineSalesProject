package com.Important.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value = "商品信息对象")
@TableName("commodity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommodityEntity implements Serializable {

    private static final long serialVersionUID = 2896832292950973394L;

    @ApiModelProperty("主键 商品id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String  commodityId;

    @ApiModelProperty("商品名称")
    @TableField("trade_name")
    private String  tradeName;

    @ApiModelProperty("商品价格")
    @TableField("commodity_price")
    private Double  commodityPrice;

    @ApiModelProperty("商品单位")
    @TableField("commodity_unit")
    private String  commodityUnit;

    @ApiModelProperty("供货商id")
    @TableField("supplier_id")
    private String  supplierId;

    @ApiModelProperty("商品库存")
    @TableField("commodity_inventory")
    private Integer  commodityInventory;

    @ApiModelProperty("商品描述")
    @TableField("product_description")
    private String  productDescription;

    @ApiModelProperty("商品图片")
    @TableField("product_picture")
    private String  productPicture;

    @ApiModelProperty("商品类型0：粮油米面 1：农副加工 2：新鲜水果 3：肉蛋鲜蔬 4：其他产品")
    @TableField("commodity_type")
    private Integer  commodityType;

    @ApiModelProperty("0:下架 1：正常")
    @TableField("commodity_status")
    private Integer  commodityStatus;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    private LocalDateTime creationTime;
    /**
     * 创建乐观锁
     */
    @Version
    @ApiModelProperty("乐观锁版本号")
    private int version;

}
