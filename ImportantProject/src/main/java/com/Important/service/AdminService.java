package com.Important.service;

import com.Important.entity.SupplierEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;

import java.util.List;

public interface AdminService {
    /**
     * 分页查询供应商申请列表
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     */
    Page<SupplierEntity> querySupplierData(String startTime, String endTime, Integer page, Integer pageSize,Integer  isSee);

    /**
     * 管理员审核用户申请成为供应商信息
     * @param supplierId
     * @param result
     * @param supplement
     */
    void auditInformation(String  supplierId,Integer result,String supplement);
}
