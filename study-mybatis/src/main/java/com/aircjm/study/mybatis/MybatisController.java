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


    @RequestMapping("/test/select")
    public void test() {
        UcUser ucUser = ucUserMapper.selectOne(1L);
        System.out.println(JSONUtil.toJsonStr(ucUser));
    }


    @RequestMapping("/test/update")
    public void testUpdate() {
        UcUser ucUser1 = new UcUser();
        ucUser1.setId(1086L);
        ucUser1.setUsername("cjmtest");
        ucUserMapper.updateOne(ucUser1);
        UcUser ucUser = ucUserMapper.selectOne(1086L);
    }


}
