package com.lwj.springbootexample.enumeration;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    /** 权限，读：1，写：2，审：4 */
    int value() default -1;
}
