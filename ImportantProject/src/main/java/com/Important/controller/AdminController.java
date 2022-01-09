package com.Important.controller;

import com.Important.entity.SupplierEntity;
import com.Important.service.AdminService;
import com.Important.utils.ResponseUtil;
import com.Important.vo.ResultVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.jdbc.util.ResultSetUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "管理员相关控制层")
@RestController
@RequestMapping("/AdminController")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 管理员查看审核供应商提交信息
     */
    @ApiOperation("管理员查看供应商审核信息")
    @GetMapping("/querySupplierData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime" ,value = "开始时间",required = false,paramType = "query"),
            @ApiImplicitParam(name = "endTime" ,value = "结束时间",required = false,paramType = "query"),
            @ApiImplicitParam(name = "page" ,value = "页码，默认为第一页",required = false,paramType = "query"),
            @ApiImplicitParam(name = "pageSize" ,value = "每页条数，默认为每页十条",required = false,paramType = "query"),
            @ApiImplicitParam(name = "isSee" ,value = "是否审核完成",required = false,paramType = "query")
    })
    public ResultVO<Object> querySupplierData(@RequestParam(value = "startTime",required = false) String  startTime ,@RequestParam(value = "endTime",required = false) String endTime
    ,@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,@RequestParam(value = "isSee",required = false) Integer  isSee){
        Page<SupplierEntity> supplierEntities = adminService.querySupplierData(startTime, endTime, page, pageSize,isSee);
        return ResponseUtil.ok(supplierEntities);
    }

    /**
     * 管理员审核供应商用户信息
     */
    @ApiOperation("管理员审核供应商用户信息")
    @GetMapping("/auditInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId",value = "供应商申请id",paramType = "query"),
            @ApiImplicitParam(name = "result",value = "审核结果",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "result",value = "审核结果",dataType = "Integer",paramType = "query")
    })
    public ResultVO<T>  auditInformation(@RequestParam(value = "supplierId",required = false)String supplierId,@RequestParam(value = "result")Integer result
            ,@RequestParam(value = "supplement",required = false)String  supplement){
        adminService.auditInformation(supplierId,result,supplement);
        return ResponseUtil.ok();
    }


}
