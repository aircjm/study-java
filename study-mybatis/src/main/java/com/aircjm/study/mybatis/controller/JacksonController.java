package com.aircjm.study.mybatis.controller;


import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatis.domain.SysUser;
import com.rimlook.framework.core.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/json")
public class JacksonController {


    @GetMapping("/testUp")
    public Response<SysUser> testUp() {
        SysUser sysUser  = new SysUser();
        sysUser.setName("LiLi");
        return Response.success(sysUser);
    }



    @PostMapping("/testUp")
    public Response<SysUser> testUpPost(@RequestBody SysUser sysUser) {
        log.info(JSONUtil.toJsonStr(sysUser));
        return Response.success(sysUser);
    }



}
