package com.Important.vo;


import com.Important.entity.CommodityEntity;
import com.Important.entity.SupplierEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommodityVo implements Serializable {
    @ApiModelProperty("供应商信息")
    private SupplierEntity  supplierEntity;
    @ApiModelProperty("商品信息")
    private CommodityEntity commodityEntity;
}
