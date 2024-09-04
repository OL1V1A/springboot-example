package com.lwj.springbootexample.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lwj.springbootexample.base.Result;
import com.lwj.springbootexample.config.LoginAuthInterceptor;
import com.lwj.springbootexample.mapper.SysUserMapper;
import com.lwj.springbootexample.model.SysUser;
import com.lwj.springbootexample.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<SysUser> getUserByUsername(@RequestParam("username") String username) {
        SysUser sysUser = sysUserMapper.get(where -> {
            where.eq(SysUser::getUsername, username);
        });
        return Result.success(sysUser);
    }

    @Override
    public Result<String> getUserToken(@RequestBody SysUser sysUser) {
        String token = UUID.randomUUID().toString().replace("-", "");
        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(sysUser),60*5, TimeUnit.SECONDS);
        return Result.success(token);
    }
}
