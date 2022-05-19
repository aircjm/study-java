package com.aircjm.cloud.common.exceptions;

/**
 * 断言类
 * @author aircjm
 */
public class Assert {

    public static void requireNonNull(Object obj, String message) throws BizException {
        if (null == obj) {
            throw new BizException(message);
        }
    }


    public static void requireNonNull(Object obj, String message, String code) throws BizException {
        if (null == obj) {
            throw new BizException(message, code);
        }
    }

}
