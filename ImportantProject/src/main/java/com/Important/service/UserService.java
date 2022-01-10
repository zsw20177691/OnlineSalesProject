package com.Important.service;

import com.Important.dto.UserDto;
import com.Important.entity.CommodityEntity;
import com.Important.entity.SupplierEntity;
import com.Important.entity.User;
import com.Important.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author 你就是下一个大佬
 */

public interface UserService {
    /**
     * @param username 用户名
     * @param password  密码
     * @return 带token的用户登录信息
     */
    UserVo login(String username,String   password);

    /**
     * 用户注册
     * @param userDto
     */
    void userRegistration(UserDto   userDto);

    /**
     *用户修改信息
     * @param user
     */
    void updateUserData(User user);

    Map<String,Object> queryCommodityData(Integer  commodityType, String tradeName, String supplierName, Integer  page, Integer  pageSize);
}
