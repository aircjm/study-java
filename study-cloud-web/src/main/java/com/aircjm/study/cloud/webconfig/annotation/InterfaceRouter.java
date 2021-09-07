package com.aircjm.study.cloud.webconfig.annotation;

import java.lang.annotation.*;

/**
 * 路由注解
 *
 * @author aircjm
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterfaceRouter {

    /**
     * client类
     *
     * @return Class
     */
    Class<?> clientClass() default Class.class;

    /**
     * client方法名
     *
     * @return 方法名
     */
    String methodName() default "";

    /**
     * switchUrl
     *
     * @return url
     */
    String switchUrl() default "";
}

