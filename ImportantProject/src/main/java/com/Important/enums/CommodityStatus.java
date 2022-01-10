package com.Important.enums;

import lombok.Getter;

public enum CommodityStatus {
    NORMAL(1,"正常"),
    INVALID(0, "下架")
    ;

    @Getter
    private Integer value;
    @Getter
    private String desc;

    CommodityStatus(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     *  通过值获取对应的FileType
     * @param value
     * @return
     */
    public static CommodityStatus getCommodityStatus(Integer value){
        CommodityStatus[] values = CommodityStatus.values();
        for (CommodityStatus val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }
}
