package com.aircjm.study.mybatis;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.mybatis.domain.UcUser;
import com.aircjm.study.mybatis.mapper.UcUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UcUserMapper mapper = sqlSession.getMapper(UcUserMapper.class);

        UcUser ucUser = mapper.selectOne(0L);
        System.out.println(JSONUtil.toJsonStr(ucUser));

        sqlSession.close();

    }
}
