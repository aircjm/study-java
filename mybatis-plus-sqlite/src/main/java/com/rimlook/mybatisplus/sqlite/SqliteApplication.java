package com.rimlook.mybatisplus.sqlite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. cd target
 * 2. java -jar mybatis-plus-sample-sqlite-0.0.1-SNAPSHOT.jar
 * 3. curl http://127.0.0.1:8080/test
 * @author nieqiuqiu
 */
@SpringBootApplication
public class SqliteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqliteApplication.class, args);
    }

}
