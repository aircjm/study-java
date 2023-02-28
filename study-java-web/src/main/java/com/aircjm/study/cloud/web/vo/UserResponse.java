package com.aircjm.study.cloud.web.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private String name;


    private Integer age;


    private LocalDateTime createTime;


    public UserResponse(String name, Integer age, LocalDateTime createTime) {
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }
}
