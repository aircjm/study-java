package com.aircjm.study.cloud.web.validator;

import com.google.common.primitives.Ints;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


/**
 * 校验给定的Int是否属于数组
 * @author aircjm
 */
public class IntInArrayValidator implements ConstraintValidator<IntInArray, Integer> {

    private List<Integer> target;

    @Override
    public void initialize(IntInArray constraintAnnotation) {
        int[] value = constraintAnnotation.target();
        target = Ints.asList(value);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 如果字段不存在值，则跳过校验。判空交给@NotNull等校验
        if (null == value || value < 0) {
            return true;
        }
        // 如果注解未写target，则和未加注解等同，直接跳过校验
        if (CollectionUtils.isEmpty(target)) {
            return true;
        }
        return target.contains(value);
    }
}
