package com.aircjm.study.mybatis;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatis.domain.UcUser;
import com.aircjm.study.mybatis.mapper.UcUserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {


    @Resource
    private UcUserMapper ucUserMapper;


    @RequestMapping("/test")
    public void test() {
        UcUser ucUser = ucUserMapper.selectOne(1L);
        System.out.println(JSONUtil.toJsonStr(ucUser));
    }


}
