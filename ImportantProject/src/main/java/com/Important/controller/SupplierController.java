package com.Important.controller;

import com.Important.dto.CommodityDto;
import com.Important.entity.CommodityEntity;
import com.Important.entity.SupplierEntity;
import com.Important.service.SupplierService;
import com.Important.utils.ResponseUtil;
import com.Important.utils.testVserionLock;
import com.Important.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "供应商相关控制层")
@RestController
@RequestMapping("/SupplierController")
@Slf4j
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    @Resource
    private testVserionLock testVserionLocak;

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

    /**
     * 供货商用户下架商品
     */
    @ApiOperation("供货商管理商品")
    @PostMapping("/ManageGoods")
    public ResultVO<T> offShelfGoods(@RequestBody CommodityEntity commodityEntity){
        supplierService.offShelfGoods(commodityEntity);
        return  ResponseUtil.ok();
    }


}
