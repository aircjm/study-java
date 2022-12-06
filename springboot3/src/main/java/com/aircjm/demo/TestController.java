package com.aircjm.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "test")
@RestController
public class TestController {


    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
