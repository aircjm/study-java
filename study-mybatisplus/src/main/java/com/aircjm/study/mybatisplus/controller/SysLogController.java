package com.aircjm.study.mybatisplus.controller;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatisplus.domain.SysLog;
import com.aircjm.study.mybatisplus.domain.SysUser;
import com.aircjm.study.mybatisplus.mapper.SysLogMapper;
import com.aircjm.study.mybatisplus.mapper.SysUserMapper;
import com.aircjm.study.mybatisplus.vo.SysLogRequestJson;
import com.rimlook.framework.core.pojo.Response;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys_log")
public class SysLogController {


    @Resource
    private SysLogMapper sysLogMapper;


    @GetMapping("/test/add")
    public Response<SysLog> test() {
        SysLog sysLog = new SysLog();
        SysLogRequestJson requestJson = new SysLogRequestJson();
        requestJson.setName("test");
        requestJson.setContext("{}");
        sysLog.setRequestJson(requestJson);
        sysLogMapper.insert(sysLog);
        return Response.success(sysLog);
    }


    @GetMapping("/test/getById")
    public Response<SysLog> getById(@RequestParam Long id) {
        return Response.success(sysLogMapper.selectById(id));
    }


}
