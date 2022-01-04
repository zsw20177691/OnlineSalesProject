package com.Important.vo;

import com.Important.entity.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -5103244482885810978L;
    private String token;
    private User    user;
}
