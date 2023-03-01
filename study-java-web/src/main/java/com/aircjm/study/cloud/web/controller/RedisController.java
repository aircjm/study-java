package com.aircjm.study.cloud.web.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("testSetAndGet")
    public String testSetAndGet() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        Long increment = operations.increment("test:inc");
        return String.valueOf(increment);
    }

}
