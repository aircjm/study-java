package com.aircjm.study.validate.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author aircjm
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotSpecialCharValidator.class)
public @interface NotSpecialChar {

    /**
     * 默认返回错误信息
     * @return 默认返回错误信息
     */
    String message() default "{param use Special Char}";

    /**
     * 分组校验
     * @return 类型
     */
    Class<?>[] groups() default {};

    /**
     * 有效载重
     * @return 类型
     */
    Class<? extends Payload>[] payload() default {};
}
