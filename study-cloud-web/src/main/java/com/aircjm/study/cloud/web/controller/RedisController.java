package com.aircjm.study.cloud.web.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/redis")
public class RedisController {


    @Resource
    private RedisTemplate redisTemplate;



    @GetMapping("testSetAndGet")
    public String testSetAndGet() {

        return "redis test";

    }

}
