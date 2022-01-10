package com.Important.controller;

import com.Important.dto.CommodityDto;
import com.Important.entity.SupplierEntity;
import com.Important.service.SupplierService;
import com.Important.utils.ResponseUtil;
import com.Important.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "供应商相关控制层")
@RestController
@RequestMapping("/SupplierController")
@Slf4j
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    /**
     * 申请成为供应商，提交管理员查看
     * @param supplierEntity
     * @return
     */
    @ApiOperation("申请成为供应商")
    @PostMapping("BecomeSupplier")
    public ResultVO<T> becomeSupplier(@RequestBody SupplierEntity supplierEntity){
        supplierService.becomeSupplier(supplierEntity);
        return ResponseUtil.ok();
    }

    /**
     * 供货商用户上架商品
     */

    @ApiOperation("供货商用户上架商品")
    @PostMapping("/GoodsOnTheShelves")
    public ResultVO<T> goodsOnTheShelves(@RequestBody @Valid CommodityDto commodityDto){
        supplierService.goodsOnTheShelves(commodityDto);
        return ResponseUtil.ok();
    }

}
