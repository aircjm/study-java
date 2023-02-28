package com.aircjm.study.cloud.web.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.web.vo.UserRequest;
import com.aircjm.study.cloud.web.vo.UserResponse;
import com.rimlook.framework.core.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author aircjm
 */
@RestController
@Slf4j
@RequestMapping(value = "/time")
public class LocalDateTimeController {


    @GetMapping("/serial")
    public Response<UserResponse> serial() {
        UserResponse response = new UserResponse("测试", 15, LocalDateTime.now());
        return Response.success(response);
    }


    @PostMapping("/deserial")
    public Response<UserResponse> deserial(@RequestBody UserRequest request) {
        log.info("user request is {}", JSONUtil.toJsonStr(request));
        UserResponse userResponse = BeanUtil.copyProperties(request, UserResponse.class);
        return Response.success(userResponse);
    }

    @GetMapping("/paramTime")
    public Response<UserResponse> timeRequestParam(@RequestParam(value = "time") LocalDateTime time) {
        log.info("user request is {}", time.toString());
        return Response.success();
    }


    @GetMapping("/localDateTimeVariable/{time}")
    public Response<UserResponse> timeVariable(@PathVariable(value = "time") LocalDateTime time) {
        log.info("user request is {}", time.toString());
        return Response.success();
    }


    @GetMapping("/dateParam")
    public Response<UserResponse> dateVariable(@RequestParam(value = "date") Date date) {
        log.info("user request is {}", date.toString());
        return Response.success();
    }


    /**
     * 解析失败
     *
     * @param date date
     * @return
     */
    @GetMapping("/datePath/{date}")
    public Response datePath(@PathVariable(value = "date") Date date) {
        log.info("user request is {}", date.toString());
        return Response.success();
    }


}
