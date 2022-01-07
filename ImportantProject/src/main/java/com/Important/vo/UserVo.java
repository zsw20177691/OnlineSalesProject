package com.Important.vo;

import com.Important.entity.MenulEntity;
import com.Important.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -5103244482885810978L;
    private String token;
    private User    user;
    private List<MenulEntity> menulEntities;
}
