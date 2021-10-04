package com.aircjm.study.validate.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class HibernateValidatorConfiguration {



    @Bean
    public static Validator validator() {
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .failFast(true) //快速返回模式，有一个验证失败立即返回错误信息
                .buildValidatorFactory()
                .getValidator();
    }

}
