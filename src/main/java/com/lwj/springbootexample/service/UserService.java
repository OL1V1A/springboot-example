package com.lwj.springbootexample.service;

import com.lwj.springbootexample.base.Result;
import com.lwj.springbootexample.model.SysUser;

public interface UserService {

    Result<SysUser> getUserByUsername(String username);

    Result<String> getUserToken(SysUser sysUser);
}
