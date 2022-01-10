package com.Important.service;

import com.Important.dto.CommodityDto;
import com.Important.entity.SupplierEntity;

public interface SupplierService {
    /**
     * 提交申请成为供应商
     * @param supplierEntity
     */
    void becomeSupplier(SupplierEntity supplierEntity);

    /**
     * 供货商用户上传商品
     * @param commodityDto
     */
    void goodsOnTheShelves(CommodityDto commodityDto);
}
