package com.Important.service.serviceImpl;

import com.Important.dto.CommodityDto;
import com.Important.entity.CommodityEntity;
import com.Important.entity.SupplierEntity;
import com.Important.enums.AuditStatusEnum;
import com.Important.mapper.CommodityMapper;
import com.Important.mapper.SupplierEntityMapper;
import com.Important.service.SupplierService;
import com.Important.utils.TimeFormatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static java.lang.Thread.sleep;

@Service
public class SupplierServiceImpl    implements SupplierService {

    @Resource
    private SupplierEntityMapper supplierEntityMapper;

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public void becomeSupplier(SupplierEntity supplierEntity) {
        supplierEntity.setAuditStatus(AuditStatusEnum.NOTVIEWED.getValue());
        supplierEntity.setApplicationTime(TimeFormatUtil.nowDateTime());
        supplierEntityMapper.insert(supplierEntity);
    }

    @Override
    public void goodsOnTheShelves(CommodityDto commodityDto) {
        CommodityEntity build = CommodityEntity.builder()
                .tradeName(commodityDto.getTradeName())
                .commodityPrice(commodityDto.getCommodityPrice())
                .commodityUnit(commodityDto.getCommodityUnit())
                .commodityInventory(commodityDto.getCommodityInventory())
                .productDescription(commodityDto.getProductDescription())
                .productPicture(commodityDto.getProductPicture())
                .commodityType(commodityDto.getCommodityType())
                .commodityStatus(1)
                .creationTime(TimeFormatUtil.nowDateTime())
                .supplierId(commodityDto.getSupplierId())
                .build();
        commodityMapper.insert(build);
    }

    @Override
    public void offShelfGoods(CommodityEntity commodityEntity) {
        commodityMapper.update(commodityEntity,new QueryWrapper<CommodityEntity>().eq("commodity_id",commodityEntity.getCommodityId()));
    }


}
