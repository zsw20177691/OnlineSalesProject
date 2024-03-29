package com.Important.service.serviceImpl;

import com.Important.dto.LoginUserDto;
import com.Important.entity.User;
import com.Important.mapper.UserMapper;
import com.Important.service.UserService;
import com.Important.utils.EncryptionUtil;
import com.Important.utils.ExceptionResult;
import com.Important.utils.GlobalExceptionInterceptor;
import com.Important.utils.JwtUserLogin;
import com.Important.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUserLogin    jwtUserLogin;
    @Resource
    private GlobalExceptionInterceptor globalExceptionInterceptor;

    @Override
    public UserVo login(String username, String   password){
        UserVo userVo = new UserVo();
        isUserExist(username);
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
        userVo.setUser(user1);
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUsername(username);
        loginUserDto.setPassword(password);
        userVo.setToken(jwtUserLogin.createToken(loginUserDto));
        return userVo;
    }
    /**
     * 检查用户登录名是否存在
     */
    public  void   isUserExist(String username ){
        User user = userMapper.selectOne(new QueryWrapper<User>().select("username").eq("username",username));
        if (StringUtils.isEmpty(user)){
            throw  new ExceptionResult("用户不存在，请先注册");
        }
    }
}


