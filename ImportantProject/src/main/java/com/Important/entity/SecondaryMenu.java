package com.Important.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
public class SecondaryMenu implements Serializable {
    private static final long serialVersionUID = -3716056545591370288L;

    private String  mainMenu;
    private String  menuUrl;
    private String  menuName;
}
