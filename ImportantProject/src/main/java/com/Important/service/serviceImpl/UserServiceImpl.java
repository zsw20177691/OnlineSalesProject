package com.Important.service.serviceImpl;

import com.Important.dto.LoginUserDto;
import com.Important.dto.UserDto;
import com.Important.entity.User;
import com.Important.enums.StatusType;
import com.Important.enums.UserType;
import com.Important.mapper.UserMapper;
import com.Important.service.UserService;
import com.Important.utils.*;
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
    public UserVo login(String telephone, String   password){
        UserVo userVo = new UserVo();
        isUserExist(telephone);
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("telephone", telephone).eq("password", password));
        if (user1.getUserStart().equals(StatusType.INVALID.getValue())){
            throw new ExceptionResult("用户已失效");
        }
        userVo.setUser(user1);
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setTelephone(telephone);
        loginUserDto.setPassword(password);
        userVo.setToken(jwtUserLogin.createToken(loginUserDto));
        userVo.setUser(user1);
        return userVo;
    }

    @Override
    public void userRegistration(UserDto userDto) {
        isUserTelephoneExist(userDto.getTelephone());
        UserType userType = UserType.getUserType(userDto.getUserType());
        if (StringUtils.isEmpty(userType)){
            throw  new ExceptionResult("用户类型错误");
        }
        User build = User.builder()
                .password(  EncryptionUtil.getMD5Str(userDto.getPawword()))
                .telephone(userDto.getTelephone())
                .url(userDto.getUrl())
                .userType(userDto.getUserType())
                .username(userDto.getUserName())
                .userStart(StatusType.NORMAL.getValue())
                .build();
        userMapper.insert(build);
    }

    @Override
    public void updateUserData(User user) {
        userMapper.update(user, new QueryWrapper<User>().eq("UUID", user.getUuid()));
    }


    /**
     * 验证点号码是否被注册
     */
    public void isUserTelephoneExist(String telephone){
        User telephone1 = userMapper.selectOne(new QueryWrapper<User>().eq("telephone", telephone));
        if (!StringUtils.isEmpty(telephone1)){
            throw new ExceptionResult("此号码号码已被注册");
        }
    }

    /**
     * 检查用户登录名是否存在
     */
    public  void   isUserExist(String username ){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("telephone",username));
        if (StringUtils.isEmpty(user)){
            throw  new ExceptionResult("用户不存在，请先注册");
        }
    }
}


