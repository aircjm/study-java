package com.aircjm.study.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aircjm.study.mybatisplus.mapper")
public class StudyMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyMybatisPlusApplication.class, args);
    }

}
