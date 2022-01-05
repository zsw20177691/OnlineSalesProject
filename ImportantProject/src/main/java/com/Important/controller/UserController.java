package com.Important.controller;


import com.Important.dto.LoginUserDto;
import com.Important.dto.UserDto;
import com.Important.entity.FileEntity;
import com.Important.entity.User;
import com.Important.service.UserService;
import com.Important.utils.ExceptionResult;
import com.Important.utils.FileUtil;
import com.Important.utils.ResponseUtil;
import com.Important.utils.SendMessageUtil;
import com.Important.vo.ResultVO;
import com.Important.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/User")
@Api(tags = "用户相关控制层")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private FileUtil fileEntity;

    private String verificationCode;

    private String telephone;

    @ApiOperation("登录")
    @PostMapping("/login")
    public UserVo login(@RequestBody @Valid LoginUserDto loginUserDto,HttpServletRequest httpServletRequest){
        log.info("收到参数;账号"+ loginUserDto.getUsername()+" 密码："+loginUserDto.getPassword());
        HttpSession session = httpServletRequest.getSession();
        session.setMaxInactiveInterval(60);
        UserVo login = userService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        return login;
    }


    @ApiOperation("文件上传")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "files", value = "文件流对象", required = false,dataType = "string")
    )
    @PostMapping("/uploadFile")
    public void   uploadFile(@RequestParam("files") MultipartFile[]   files){
        fileEntity.updatePath(files);
        System.out.println("查看请求是否有相应");
    }

    @ApiOperation("用户注册")
    @PostMapping("/UserRegistration")
    public ResultVO<T> userRegistration(@RequestBody @Valid UserDto userDto){
        if (!userDto.getVerificationCode().equals(verificationCode)){
            throw new ExceptionResult("验证码不正确");
        }
        if (!userDto.getTelephone().equals(telephone)){
            throw new ExceptionResult("请输入同一电话号码");
        }
        userService.userRegistration(userDto);
        return ResponseUtil.ok();
    }

    @ApiOperation("获取验证码")
    @GetMapping("/VerificationCode")
    public String verificationCode(@RequestParam String iphone){
        if (!Pattern.matches("^[1][3,4,5,7,8,9][0-9]{9}$", iphone)){
            throw  new ExceptionResult("手机号格式不正确");
        }
        String randomCode = SendMessageUtil.getRandomCode(6);
        SendMessageUtil.send("你就是下一个大佬", "d41d8cd98f00b204e980", "18173425291", "验证码:" + randomCode);
        verificationCode=randomCode;
        telephone=iphone;
        return randomCode;
    }

}
