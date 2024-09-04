package com.lwj.springbootexample.mapper;

import cn.mybatis.mp.core.mybatis.mapper.MybatisMapper;
import com.lwj.springbootexample.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends MybatisMapper<SysUser> {

}
