package com.aircjm.study.mybatisplus.controller;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatisplus.domain.SysUser;
import com.aircjm.study.mybatisplus.mapper.SysUserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mybatisplus")
public class MybatisController {


    @Resource
    private SysUserMapper userMapper;


    @RequestMapping("/test/select")
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        // 一级缓存需要在一个事务里面
            SysUser ucSysUser = userMapper.selectOne(1L);
            SysUser ucSysUse2r = userMapper.selectOne(1L);
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
