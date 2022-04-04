package com.aircjm.study.cloud.web.controller;


import com.aircjm.study.cloud.web.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private TestService testService;


    @GetMapping("/test")
    public String test() {
        return "success";
    }


    @GetMapping("/testService")
    public String testService() {
        testService.test();
        return "success";
    }

}
