package com.jovan.wxjava.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author Jovan
 * @create 2019-05-02 19:48
 */
@Data
public class RestResult<T> implements Serializable {

    public static final Integer CODE_OK = 0;
    public static final Integer CODE_FAIL = 1;

    private Integer code;
    private String msg;
    private T data;
    private String result;

    private RestResult() {
    }

    public static <T> RestResult<T> newInstance() {
        return new RestResult<T>();
    }
}
