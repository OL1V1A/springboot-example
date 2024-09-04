package com.lwj.springbootexample.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.lwj.springbootexample.base.Result;
import com.lwj.springbootexample.config.LoginAuthInterceptor;
import com.lwj.springbootexample.enumeration.Auth;
import com.lwj.springbootexample.model.SysUser;
import com.lwj.springbootexample.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;


@RestController
public class LoginController {

    @Resource
    UserService userService;

    @PostMapping("login")
    public Result<String> login(@RequestParam("username") String username,
                              @RequestParam("password")String password){
        SysUser sysUser = Result.check(userService.getUserByUsername(username));
        if (Objects.isNull(sysUser)){
            return Result.error("用户不存在");
        }
        if (!Objects.equals(sysUser.getPassword(), DigestUtil.md5Hex(password))){
            return Result.error("密码错误");
        }
        String token = Result.check(userService.getUserToken(sysUser));
        return Result.success(token);
    }

    @GetMapping("getNickname")
    @Auth()
    public Result<String> getNickname(){
        String userNickName = LoginAuthInterceptor.getUserNickName();
        return Result.success(userNickName);
    }
    @GetMapping("logout")
    @Auth(2)
    public Result<String> logout(){
        String userNickName = LoginAuthInterceptor.getUserNickName();
        return Result.success(userNickName);
    }

}
