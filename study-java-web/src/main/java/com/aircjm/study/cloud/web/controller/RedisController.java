package com.aircjm.study.cloud.web.controller;

import com.rimlook.framework.core.pojo.Response;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate  redisTemplate;

    @GetMapping("/testIncrement")
    public String testIncrement() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        Long increment = operations.increment("test:inc");
        return String.valueOf(increment);
    }

    @GetMapping("/testSetAndGet")
    public String testSetAndGet() {
        Long increment = redisTemplate.boundValueOps("test:inc").increment();
        return String.valueOf(increment);
    }



    @GetMapping("/keys")
    public Response<Set<String>> keys() {
        // todo keys no out print
        Set<String> keys = redisTemplate.keys("*");
        return Response.success(keys);
    }

}
