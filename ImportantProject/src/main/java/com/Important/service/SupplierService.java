package com.Important.service;

import com.Important.dto.CommodityDto;
import com.Important.entity.CommodityEntity;
import com.Important.entity.SupplierEntity;
import com.Important.vo.ResultVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * 供货商管理自己商品
     * @param commodityEntity
     */
    void offShelfGoods(CommodityEntity commodityEntity);
}
