package com.Important.controller;


import com.Important.dto.LoginUserDto;
import com.Important.dto.UserDto;
import com.Important.entity.CommodityEntity;
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
import java.util.Map;
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
        log.info("收到参数;账号"+ loginUserDto.getTelephone()+" 密码："+loginUserDto.getPassword());
        HttpSession session = httpServletRequest.getSession();
        session.setMaxInactiveInterval(60);
        UserVo login = userService.login(loginUserDto.getTelephone(), loginUserDto.getPassword());
        return login;
    }


    @ApiOperation("文件上传")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "files", value = "文件流对象", required = false,dataType = "string")
    )
    @PostMapping("/uploadFile")
    public void   uploadFile(@RequestParam("files") MultipartFile[]   files){
        fileEntity.updatePath1(files);
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

    @ApiOperation("修改个人信息")
    @PostMapping("/UpdateUserData")
    public ResultVO<T>  updateUserData(@RequestBody User user ){
        userService.updateUserData(user);
        return ResponseUtil.ok();
    }

    @ApiOperation("用户查看商品列表")
    @GetMapping("/QueryCommodityData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityType",value = "商品类型 0：粮油米面 1：农副加工 2：新鲜水果 3：肉蛋鲜蔬 4：其他产品",paramType = "query"),
            @ApiImplicitParam(name = "tradeName",value = "商品名称",paramType = "query"),
            @ApiImplicitParam(name = "supplierName",value = "供货商店铺名称",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码-默认为第一页",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页条数-默认为第十页",paramType = "query"),
    })
    public ResultVO<Object>  queryCommodityData(@RequestParam(value = "commodityType",required = false)Integer  commodityType,
        @RequestParam(value = "tradeName",required = false)String tradeName,@RequestParam(value = "supplierName",required = false)String supplierName,
        @RequestParam(value = "page",required = false,defaultValue = "1")Integer  page,  @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer  pageSize){
        Map<String, Object> map = userService.queryCommodityData(commodityType, tradeName, supplierName, page, pageSize);
        return ResponseUtil.ok(map);
    }


}
