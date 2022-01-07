package com.Important.enums;

import lombok.Getter;

public enum AuditStatusEnum {
    NOTVIEWED(0, "未查看"),
    ADOPT(1,"通过"),
    FAIL(2,"失败")
    ;


    @lombok.Getter
    private Integer value;
    @Getter
    private String desc;

    AuditStatusEnum(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     *  通过值获取对应的FileType
     * @param value
     * @return
     */
    public static AuditStatusEnum AuditStatusEnum(Integer value){
        AuditStatusEnum[] values = AuditStatusEnum.values();
        for (AuditStatusEnum val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }
}
