package com.Important.utils;

import com.Important.entity.CommodityEntity;
import com.Important.mapper.CommodityMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class testVserionLock {

    @Resource
    private CommodityMapper commodityMapper;

    /**
     * 测试乐观锁悲观锁效果
     */
    @Async
    public  void testVsesion(){
        log.info("任务1"+Thread.currentThread().getName()+"数量加五十 任务一" );
        CommodityEntity commodityEntity = commodityMapper.selectOne(new  QueryWrapper().select("*").last(true,"where 1=1 and commodity_id='8502ab682be272ae91ad7377c5de0e41' for update"));
        commodityEntity.setCommodityInventory(commodityEntity.getCommodityInventory()+50);
        commodityMapper.updateById(commodityEntity);
    }

    @Async
    public void testVsesionMark(){
        log.info("任务2"+Thread.currentThread().getName()+"数量减一百任务二" );
        CommodityEntity commodityEntity = commodityMapper.selectOne(new  QueryWrapper().select("*").last(true,"where 1=1 and commodity_id='8502ab682be272ae91ad7377c5de0e41' for update"));
        commodityEntity.setCommodityInventory(commodityEntity.getCommodityInventory()-100);
        commodityMapper.updateById(commodityEntity);
    }

    @Async
    public  void testVsesionMark1(){
        log.info("任务3"+Thread.currentThread().getName()+"数量加二十任务三" );
        CommodityEntity commodityEntity = commodityMapper.selectOne(new  QueryWrapper().select("*").last(true,"where 1=1 and commodity_id='8502ab682be272ae91ad7377c5de0e41' for update"));
        commodityEntity.setCommodityInventory(commodityEntity.getCommodityInventory()+20);
        commodityMapper.updateById(commodityEntity);
    }


    @Async
    public  void testVsesionMark2(){
        log.info("任务4"+Thread.currentThread().getName()+"数量加一百任务四" );
        CommodityEntity commodityEntity = commodityMapper.selectOne(new  QueryWrapper().select("*").last(true,"where 1=1 and commodity_id='8502ab682be272ae91ad7377c5de0e41' for update"));
        commodityEntity.setCommodityInventory(commodityEntity.getCommodityInventory()+100);
        commodityMapper.updateById(commodityEntity);
    }

}
