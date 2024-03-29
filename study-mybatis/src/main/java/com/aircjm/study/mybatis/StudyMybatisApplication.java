package com.aircjm.study.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aircjm.study.mybatis.mapper")
public class StudyMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyMybatisApplication.class, args);
    }

}
