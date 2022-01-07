package com.Important.service;

import com.Important.entity.SupplierEntity;

public interface SupplierService {
    /**
     * 提交申请成为供应商
     * @param supplierEntity
     */
    void becomeSupplier(SupplierEntity supplierEntity);
}
