package com.aircjm.study.mybatis;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatisplus.domain.SysUser;
import com.aircjm.study.mybatisplus.mapper.SysUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionTests {


    /**
     * 测试mybatis
     * @throws IOException
     */
    @Test
    public  void testSelect() throws IOException {
        // java8 以上适用
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatisplus-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);

        SysUser ucSysUser = mapper.selectOne(0L);
        System.out.println(JSONUtil.toJsonStr(ucSysUser));

        sqlSession.close();
    }


    /**
     * 测试mybatis
     * @throws IOException
     */
    @Test
    public  void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatisplus-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);

        SysUser ucSysUser = new SysUser();
        ucSysUser.setName("testcjm001");
        ucSysUser.setId(1086L);
        mapper.updateOne(ucSysUser);
        sqlSession.close();
    }


    public static void main(String[] args) throws IOException {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
//        testSelect();
    }

}
