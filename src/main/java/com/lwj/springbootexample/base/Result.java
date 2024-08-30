package com.lwj.springbootexample.base;

import lombok.Data;

import java.util.Objects;
@Data
public class Result<T> {

    public static final Integer SUCCESS_CODE = 0;
    public static final Integer ERROR_CODE = 500;

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, null, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ERROR_CODE, msg);
    }

    public static <T> Result<T> create(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> T check(Result<T> rsp){
        if (!Objects.equals(rsp.getCode(), SUCCESS_CODE)) {
            throw new RuntimeException(rsp.getMsg());
        }
        return rsp.getData();
    }
}
