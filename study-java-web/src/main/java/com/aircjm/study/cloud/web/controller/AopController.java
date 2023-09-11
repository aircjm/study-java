package com.aircjm.study.cloud.web.controller;


import com.aircjm.study.cloud.web.service.TestService;
import com.rimlook.framework.core.pojo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/aop")
@RestController
public class AopController {

    @Resource
    private TestService testService;


    @GetMapping("/proxy")
    public Response proxy() {


        testService.testAop();


        return Response.success();
    }


}
