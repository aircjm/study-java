package com.aircjm.study.cloud.web.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private String name;


    private Integer age;


   @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;


    public UserResponse(String name, Integer age, LocalDateTime createTime) {
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }
}
