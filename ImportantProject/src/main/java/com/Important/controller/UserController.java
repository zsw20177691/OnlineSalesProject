package com.Important.controller;


import com.Important.dto.LoginUserDto;
import com.Important.entity.FileEntity;
import com.Important.entity.User;
import com.Important.service.UserService;
import com.Important.utils.FileUtil;
import com.Important.utils.ResponseUtil;
import com.Important.vo.ResultVO;
import com.Important.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/User")
@Api(tags = "用户相关控制层")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private FileUtil fileEntity;

    @ApiOperation("登录")
    @PostMapping("/login")
    public UserVo login(@RequestBody @Valid LoginUserDto loginUserDto,HttpServletRequest httpServletRequest){
        log.info("收到参数;账号"+ loginUserDto.getUsername()+" 密码："+loginUserDto.getPassword());
        HttpSession session = httpServletRequest.getSession();
        session.setMaxInactiveInterval(60);
        UserVo login = userService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        return login;
    }

//    @ApiOperation("文件上传")
//    @PostMapping("/uploadFile")
//    public FileEntity  uploadFile( MultipartFile[]   files){
//        fileEntity.updatePath(files);
//        System.out.println("查看请求是否有相应");
//        return fileEntity;
//    }

    @ApiOperation("文件上传")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "files", value = "文件流对象", required = false,dataType = "string")
    )
    @PostMapping("/uploadFile")
    public void   uploadFile(@RequestParam("files") MultipartFile[]   files){
        fileEntity.updatePath(files);
        System.out.println("查看请求是否有相应");
//        return fileEntity;
    }


}
