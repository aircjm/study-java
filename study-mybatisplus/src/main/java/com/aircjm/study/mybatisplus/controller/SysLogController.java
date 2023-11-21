package com.aircjm.study.mybatisplus.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatisplus.domain.SysLog;
import com.aircjm.study.mybatisplus.domain.SysUser;
import com.aircjm.study.mybatisplus.mapper.SysLogMapper;
import com.aircjm.study.mybatisplus.mapper.SysUserMapper;
import com.aircjm.study.mybatisplus.vo.SysLogRequestJson;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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


    // http://localhost:8080/sys_log/test/search?name=te
    // http://localhost:8080/sys_log/test/search?nameLike=te
    @GetMapping("/test/search")
    public Response<SysLog> getById(@RequestParam(required = false) String name, @RequestParam(required = false) String nameLike) {
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<SysLog>()
                .apply(StrUtil.isNotBlank(name),"request_json -> '$.name' = {0}", name)
                .apply(StrUtil.isNotBlank(nameLike),"request_json -> '$.name' LIKE CONCAT('%',{0},'%')", nameLike)
                .last("limit 1");
        SysLog sysLog = sysLogMapper.selectOne(queryWrapper);
        return Response.success(sysLog);
    }
}
