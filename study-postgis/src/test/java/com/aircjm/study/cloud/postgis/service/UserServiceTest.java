package com.aircjm.study.cloud.postgis.service;

import com.aircjm.study.cloud.postgis.domain.User;
import com.aircjm.study.cloud.postgis.mapper.UserMappper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;


    @Test
    void addUser() {
        User user = new User();
        user.setName("tom");
        user.setEmail("tom@gmail.com");
        user.setAge(18);
        userService.addUser(user);
    }
}
