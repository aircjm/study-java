package com.aircjm.study.cloud.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @GetMapping("/test")
    public String test() {
        return "success";
    }

}
