package com.aircjm.study.mybatis;

import com.aircjm.study.mybatisplus.domain.SysUser;
import com.aircjm.study.mybatisplus.mapper.SysUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudyMybatisPlusApplicationTests {

    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<SysUser> sysUserList = userMapper.selectList(null);
        Assertions.assertEquals(5, sysUserList.size());
        sysUserList.forEach(System.out::println);
    }

}
