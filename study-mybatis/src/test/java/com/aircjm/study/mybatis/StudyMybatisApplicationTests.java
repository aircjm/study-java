package com.aircjm.study.mybatis;

import com.aircjm.study.mybatis.domain.SysUser;
import com.aircjm.study.mybatis.mapper.SysUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudyMybatisApplicationTests {

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
