package com.aircjm.cloud.common.enums;


/**
 * 异常枚举类
 *
 * @author aircjm
 */

public enum ErrorCodeEnum {


    /**
     * 无数据
     */
    NO_DATA(90001, "无数据"),

    /**
     * 外系统返回错误
     */
    OUTSIDE_SYSTEM_RETURN_ERROR(90002, "外系统返回错误"),


    /**
     * 系统内部异常
     */
    SYSTEM_ERROR(90003, "系统内部异常"),


    /**
     * 参数无效
     */
    PARAM_ERROR(90004, "参数无效"),


    /**
     * 参数不能为空
     */
    PARAM_IS_NULL_ERROR(90005, "参数不能为空"),


    /**
     * 操作频繁，请稍后再试
     */
    FREQUENT_OPERATION(90006, " 操作频繁，请稍后再试"),

    /**
     * 接口返回成功
     */
    SUCCESS(0, "success");

    Integer code;

    String msg;

    ErrorCodeEnum() {
    }

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
