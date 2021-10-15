package com.aircjm.study.cloud.webconfig.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.cloud.common.Response;
import com.aircjm.study.cloud.webconfig.vo.UserRequest;
import com.aircjm.study.cloud.webconfig.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author aircjm
 */
@RestController
@Slf4j
@RequestMapping(value = "/time")
public class LocalDateTimeController {


    @GetMapping("/serial")
    public Response<UserResponse> serial() {
        return Response.success(new UserResponse("测试", 15, LocalDateTime.now()));
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


}
