package com.Important.service.serviceImpl;

import com.Important.entity.AuditRecordEntuty;
import com.Important.entity.SupplierEntity;
import com.Important.enums.AuditStatusEnum;
import com.Important.enums.StatusType;
import com.Important.mapper.AuditRecordEntutyMapper;
import com.Important.mapper.SupplierEntityMapper;
import com.Important.service.AdminService;
import com.Important.utils.ExceptionResult;
import com.Important.utils.TimeFormatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private SupplierEntityMapper    supplierEntityMapper;

    @Resource
    private AuditRecordEntutyMapper auditRecordEntutyMapper;

    @Override
    public Page<SupplierEntity> querySupplierData(String startTime, String endTime, Integer page, Integer pageSize,Integer  isSee) {
        if (startTime.isEmpty()){
            startTime="0000-00-00 00:00:00";
        }
        if (endTime.isEmpty()){
            endTime= LocalDateTime.now().toString();
        }
        Page<SupplierEntity> supplierEntityPage = supplierEntityMapper.selectPage(new Page<>(page, pageSize), new QueryWrapper<SupplierEntity>().le("time", endTime).ge("time", startTime));
        if (!StringUtils.isEmpty(isSee)){
            List<SupplierEntity> records = supplierEntityPage.getRecords();
            List<SupplierEntity> collect = records.stream().filter(s -> s.getAuditStatus().equals(isSee)).collect(Collectors.toList());
            log.info("筛选文件"+collect);
            supplierEntityPage.setRecords(collect);
        }

        return supplierEntityPage;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void auditInformation(String supplierId, Integer result, String supplement) {
        try{
            SupplierEntity supplierEntity = supplierEntityMapper.selectOne(new QueryWrapper<SupplierEntity>().eq("supplier_id", supplierId));
            if (StringUtils.isEmpty(AuditStatusEnum.getAuditStatusEnum(result))){
                throw new ExceptionResult("审核状态修改错误");
            }
            supplierEntity.setAuditStatus(result);
            supplierEntityMapper.update(supplierEntity,new QueryWrapper<SupplierEntity>().eq("supplier_id", supplierId));
            AuditRecordEntuty auditRecordEntuty = AuditRecordEntuty.builder()
                    .auditResult(result)
                    .auditMark(supplement)
                    .supplierId(supplierId)
                    .auditTime(TimeFormatUtil.nowDateTime())
                    .supplierTime(supplierEntity.getApplicationTime())
                    .build();
            auditRecordEntutyMapper.insert(auditRecordEntuty);
        }catch (Exception e){
            throw new ExceptionResult("审核失败");
        }

    }
}
