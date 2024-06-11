package com.aircjm.study.es.web.exceptions;

import com.rimlook.framework.core.exception.BusinessException;

import java.util.Objects;

public class Assert {
    public static void requireNonNull(Object obj, String msg) {
        if (Objects.isNull(obj)) {
            throw new BusinessException(msg);
        }
    }
}
