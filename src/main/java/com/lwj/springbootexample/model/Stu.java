package com.lwj.springbootexample.model;

import cn.mybatis.mp.db.annotations.Table;
import lombok.Data;

@Data
@Table
public class Stu {

    private Integer id;

    private String name;

    private Integer age;

    private Double type;
}
