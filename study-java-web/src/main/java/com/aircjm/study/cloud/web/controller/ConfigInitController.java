package com.aircjm.study.cloud.web.controller;

import cn.hutool.core.collection.CollUtil;
import com.aircjm.study.cloud.web.config.MailConfig;
import com.rimlook.framework.core.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigInitController {


    @Resource
    private MailConfig mailConfig;



    @GetMapping("/show")
    public Response<String> getLongType() {
        return Response.success(CollUtil.join(mailConfig.getCc(), ","));
    }

}
