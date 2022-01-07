package com.Important.service.serviceImpl;

import com.Important.entity.SupplierEntity;
import com.Important.enums.AuditStatusEnum;
import com.Important.mapper.SupplierEntityMapper;
import com.Important.service.SupplierService;
import com.Important.utils.TimeFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SupplierServiceImpl    implements SupplierService {

    @Resource
    private SupplierEntityMapper supplierEntityMapper;

    @Override
    public void becomeSupplier(SupplierEntity supplierEntity) {
        supplierEntity.setAuditStatus(AuditStatusEnum.NOTVIEWED.getValue());
        supplierEntity.setApplicationTime(TimeFormatUtil.nowDateTime());
        supplierEntityMapper.insert(supplierEntity);
    }
}
