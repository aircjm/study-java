package com.aircjm.study.cloud.web.controller;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.web.vo.LongStringSerializerVo;
import com.rimlook.framework.core.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/json")
@Slf4j
public class JsonController {


    @Resource
    private SnowflakeGenerator snowflakeGenerator;



    @GetMapping("/getLongType")
    public Response<LongStringSerializerVo> getLongType() {
        LongStringSerializerVo result = new LongStringSerializerVo();
        // 前端使用number类型会数据溢出 会导致前端接受数据时丢失精度，通过添加注解可以解决这个问题
        result.setId(snowflakeGenerator.next());
        result.setCreateBy(snowflakeGenerator.next());
        log.info(JSONUtil.toJsonStr(result));
        // {"id":1631472076407701504,"createBy":1631472076407701505}
        // createBy 前端number类型展示在页面时是1631472076407701500 会丢失精度
        return Response.success(result);
    }

}
