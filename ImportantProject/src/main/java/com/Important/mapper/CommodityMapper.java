package com.Important.mapper;

import com.Important.entity.CommodityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<CommodityEntity> {

    @Select("<script>"
            +"select *from commodity as a right join supplier as b on a.supplier_id=b.supplier_id where 1=1"
            +"<if test='commodityType!=null and commodityType!=\"\"'>"
            +" and a.commodity_type=#{commodityType}"
            +"</if>"
            +"<if test='tradeName!=null and tradeName!=\"\"'>"
            +" and a.trade_name like  concat('%',#{tradeName},'%')  "
            +"</if>"
            +"<if test='supplierName!=null and supplierName!=\"\"'>"
            +" and b.supplier_name like  concat('%',#{supplierName},'%')"
            +"</if>"
            +" limit #{page},#{pageSize}"
            +"</script>")
    List<CommodityEntity> queryCommodityData(@Param("commodityType") Integer commodityType,@Param("tradeName") String tradeName,@Param("supplierName") String supplierName,@Param("page") Integer page,@Param("pageSize") Integer pageSize);

    @Select("<script>"
            +"select count(*) from commodity as a right join supplier as b on a.supplier_id=b.supplier_id where 1=1"
            +"<if test='commodityType!=null and commodityType!=\"\"'>"
            +" and a.commodity_type=#{commodityType}"
            +"</if>"
            +"<if test='tradeName!=null and tradeName!=\"\"'>"
            +" and a.trade_name like  concat('%',#{tradeName},'%')  "
            +"</if>"
            +"<if test='supplierName!=null and supplierName!=\"\"'>"
            +" and b.supplier_name like  concat('%',#{supplierName},'%')"
            +"</if>"
            +"</script>")
    Integer queryCommodityDataCount(@Param("commodityType") Integer commodityType,@Param("tradeName") String tradeName,@Param("supplierName") String supplierName);
}
