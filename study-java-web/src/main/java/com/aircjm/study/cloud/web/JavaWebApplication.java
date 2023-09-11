package com.aircjm.study.cloud.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class JavaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebApplication.class, args);
    }

}
