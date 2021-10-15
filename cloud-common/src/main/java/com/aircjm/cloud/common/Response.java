package com.aircjm.cloud.common;

import java.util.Objects;

/**
 * 返回实体类
 * @author aircjm
 */
public class Response<T> {

    private Integer code;

    private String msg;

    private T data;

    private static final Integer SUCCESS = 0;

    public static Response success(Object object) {
        return new Response<>(SUCCESS, "success", object);
    }



    public static Response success() {
        return new Response<>(SUCCESS, "success", null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Response() {
    }

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response<String> fail(Integer code, String msg) {
        return new Response<>(code, msg, null);
    }

}
