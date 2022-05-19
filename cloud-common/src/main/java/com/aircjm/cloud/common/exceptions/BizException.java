package com.aircjm.cloud.common.exceptions;


/**
 * 自定义异常
 *
 * @author aircjm
 */
public class BizException extends RuntimeException {

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;


    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BizException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BizException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BizException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BizException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }


// todo  需要支持i18n多语言配置
//    @Override
//    public String getMessage() {
//        String message = null;
//        if (!StringUtils.isEmpty(code)) {
//            message = MessageUtils.message(code, args);
//        }
//        if (message == null) {
//            message = defaultMessage;
//        }
//        return message;
//    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
