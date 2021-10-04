package com.aircjm.study.cloud.postgis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aircjm.study.cloud.postgis.mapper")
public class StudyPostgisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyPostgisApplication.class, args);
    }

}
