package com.Important.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommodityDto implements Serializable {

    private static final long serialVersionUID = -5226103023848763182L;

    @ApiModelProperty("商品名称")
    @NotNull(message = "商品名称，不能为空")
    @NotEmpty(message = "商品名称，不能空字符")
    private String  tradeName;

    @ApiModelProperty("商品价格")
    @NotNull(message = "商品单位，不能为空")
    @Min(message = "商品价格必须大于 0",value = 0)
    private Double  commodityPrice;

    @ApiModelProperty("商品单位")
    @NotNull(message = "商品单位，不能为空")
    @NotEmpty(message = "商品单位，不能空字符")
    private String  commodityUnit;

    @ApiModelProperty("供货商id")
    @NotNull(message = "商品单位，不能为空")
    @NotEmpty(message = "商品单位，不能空字符")
    private String  supplierId;

    @ApiModelProperty("商品库存")
    @NotNull(message = "商品单位，不能为空")
    @Min(value = 0,message = "商品库存必须大于0")
    private Integer  commodityInventory;

    @ApiModelProperty("商品描述")
    @NotNull(message = "商品单位，不能为空")
    @NotEmpty(message = "商品单位，不能空字符")
    private String  productDescription;

    @ApiModelProperty("商品图片")
    @TableField("product_picture")
    private String  productPicture;

    @ApiModelProperty("商品类型0：粮油米面 1：农副加工 2：新鲜水果 3：肉蛋鲜蔬 4：其他产品")
    @TableField("commodity_type")
    private Integer  commodityType;
}
