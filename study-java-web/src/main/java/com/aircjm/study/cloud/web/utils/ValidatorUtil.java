package com.aircjm.study.cloud.web.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 手动进行校验 工具类
 *
 * @author aircjm
 */
public class ValidatorUtil {
    private static final Validator validator;

    static {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    /**
     * javax.validation注解校验
     *
     * @param t 将要校验的对象
     */
    public static <T> List<String> validate(T t) {
        return validate(t, null);
    }

    /**
     * 分组进行校验
     *
     * @param t      请求参数
     * @param groups 组名集合
     * @param <T>    参数类型
     * @return 错误信息结果  如果为空代码校验通过
     */
    public static <T> List<String> validate(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> set;
        if (groups != null && groups.length > 0) {
            set = validator.validate(t, groups);
        } else {
            set = validator.validate(t);
        }
        List<String> validateError = new ArrayList<>();
        if (set.size() > 0) {
            for (ConstraintViolation<T> val : set) {
                validateError.add(val.getMessage());
            }
        }
        return validateError;
    }
}

