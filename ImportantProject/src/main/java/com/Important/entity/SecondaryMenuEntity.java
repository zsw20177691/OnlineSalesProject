package com.Important.entity;


import lombok.Data;

import java.io.Serializable;


@Data
public class SecondaryMenuEntity implements Serializable {
    private static final long serialVersionUID = -3716056545591370288L;

    private String  mainMenu;
    private String  menuUrl;
    private String  menuName;
}
