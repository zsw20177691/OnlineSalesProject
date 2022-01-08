package com.Important.service.serviceImpl;

import com.Important.entity.SupplierEntity;
import com.Important.mapper.SupplierEntityMapper;
import com.Important.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private SupplierEntityMapper    supplierEntityMapper;

    @Override
    public Page<SupplierEntity> querySupplierData(String startTime, String endTime, Integer page, Integer pageSize) {
        if (startTime.isEmpty()){
            startTime="0000-00-00 00:00:00";
        }
        if (endTime.isEmpty()){
            endTime= LocalDateTime.now().toString();
        }
        Page<SupplierEntity> supplierEntityPage = supplierEntityMapper.selectPage(new Page<>(page, pageSize), new QueryWrapper<SupplierEntity>().le("time", endTime).ge("time", startTime));
        return supplierEntityPage;
    }

    @Override
    public void auditInformation(String supplierId, Integer result, String supplement) {

    }
}
