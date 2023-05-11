package com.aircjm.study.mybatis.controller;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatis.domain.SysUser;
import com.aircjm.study.mybatis.mapper.SysUserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {


    @Resource
    private SysUserMapper userMapper;


    @RequestMapping("/test/select")
    public void test() {
        SysUser ucSysUser = userMapper.selectOne(1L);
        System.out.println(JSONUtil.toJsonStr(ucSysUser));
    }


    @RequestMapping("/test/update")
    public void testUpdate() {
        SysUser ucSysUser1 = new SysUser();
        ucSysUser1.setId(1086L);
        ucSysUser1.setName("cjmtest");
        userMapper.updateOne(ucSysUser1);
        SysUser ucSysUser = userMapper.selectOne(1086L);
    }


}
