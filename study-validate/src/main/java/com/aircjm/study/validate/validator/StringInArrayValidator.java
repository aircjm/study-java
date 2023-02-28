package com.aircjm.study.validate.validator;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 校验给定的string是否属于数组
 *
 * @author aircjm
 */
public class StringInArrayValidator implements ConstraintValidator<StringInArray, String> {

    private List<String> target;

    @Override
    public void initialize(StringInArray constraintAnnotation) {
        String[] value = constraintAnnotation.target();
        target = Lists.newArrayList(value);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果字段不存在值，则跳过校验。判空交给@NotNull等校验
        if (StrUtil.isBlank(value)) {
            return true;
        }
        // 如果注解未写target，则和未加注解等同，直接跳过校验
        if (CollectionUtil.isEmpty(target)) {
            return true;
        }
        return target.contains(value);
    }
}
