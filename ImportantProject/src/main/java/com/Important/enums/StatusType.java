package com.Important.enums;

import lombok.Getter;

public enum StatusType {


    NORMAL(1,"正常"),
    INVALID(0, "失效")
    ;

    @Getter
    private Integer value;
    @Getter
    private String desc;

    StatusType(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     *  通过值获取对应的FileType
     * @param value
     * @return
     */
    public static StatusType getStatusType(Integer value){
        StatusType[] values = StatusType.values();
        for (StatusType val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }

}
