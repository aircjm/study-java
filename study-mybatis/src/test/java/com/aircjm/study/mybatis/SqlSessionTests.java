package com.aircjm.study.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class SqlSessionTests {



    @Test
    public  void test() {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(SqlSessionTests.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));


    }

}
