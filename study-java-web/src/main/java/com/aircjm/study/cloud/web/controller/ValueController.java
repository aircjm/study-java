package com.aircjm.study.cloud.web.controller;

import com.rimlook.framework.core.pojo.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/value")
@RestController
public class ValueController {


    @Value("${sdk.apiKey:}")
    private String apiKey;


    @GetMapping("/get")
    public Response<String> get() {
        return Response.success(apiKey);
    }

}
