package com.aircjm.study.mybatis;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatis.domain.UcUser;
import com.aircjm.study.mybatis.mapper.UcUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionTests {


    /**
     * 测试mybatis
     * @throws IOException
     */
    @Test
    public  void testSelect() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UcUserMapper mapper = sqlSession.getMapper(UcUserMapper.class);

        UcUser ucUser = mapper.selectOne(0L);
        System.out.println(JSONUtil.toJsonStr(ucUser));

        sqlSession.close();
    }


    /**
     * 测试mybatis
     * @throws IOException
     */
    @Test
    public  void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UcUserMapper mapper = sqlSession.getMapper(UcUserMapper.class);

        UcUser ucUser = new UcUser();
        ucUser.setUsername("testcjm001");
        ucUser.setId(1086L);
        mapper.updateOne(ucUser);
        sqlSession.close();
    }

}
