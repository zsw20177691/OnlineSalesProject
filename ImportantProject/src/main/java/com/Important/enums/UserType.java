package com.Important.enums;

import lombok.Getter;

public enum UserType {

    ADMIN(0, "管理员"),
    STUDENT(1,"普通用户"),
    COMPANY(2,"供应商")
    ;

    @Getter
    private Integer value;
    @Getter
    private String desc;

    UserType(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     *  通过值获取对应的UserType
     * @param value
     * @return
     */
    public static UserType getUserType(Integer value){
        UserType[] values = UserType.values();
        for (UserType val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }

}
