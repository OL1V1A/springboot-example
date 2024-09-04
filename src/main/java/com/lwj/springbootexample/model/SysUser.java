package com.lwj.springbootexample.model;

import cn.mybatis.mp.db.annotations.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Table
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private Integer status;

    private Integer sex;

    private String birthday;

    private Date createTime;

    private Date updateTime;

}
