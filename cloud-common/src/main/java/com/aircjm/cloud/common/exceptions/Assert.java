package com.aircjm.cloud.common.exceptions;

/**
 * 断言类
 * @author aircjm
 */
public class Assert {

    public static void requireNonNull(Object obj, String message) throws BaseException {
        if (null == obj) {
            throw new BaseException(message);
        }
    }


    public static void requireNonNull(Object obj, String message, String code) throws BaseException {
        if (null == obj) {
            throw new BaseException(message, code);
        }
    }

}
