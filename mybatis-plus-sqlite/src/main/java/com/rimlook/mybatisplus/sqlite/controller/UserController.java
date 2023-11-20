package com.rimlook.mybatisplus.sqlite.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rimlook.mybatisplus.sqlite.entity.User;
import com.rimlook.mybatisplus.sqlite.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nieqiuqiu
 */
@RestController
@RequestMapping(value = "/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    // 测试地址 http://localhost:8080/test
    @RequestMapping(value = "test")
    public String test(){
        User user = new User();
        user.setEmail("papapapap@qq.com");
        user.setAge(18);
        user.setName("啪啪啪");
        userService.save(user);
        List<User> list = userService.list(new LambdaQueryWrapper<>(new User()).select(User::getId, User::getName));
        list.forEach(u -> LOGGER.info("当前用户数据:{}", u));
        return "papapapap@qq.com";
    }



    // 测试地址 http://localhost:8080/testEmpty
    @RequestMapping(value = "testEmpty")
    public String testEmpty(){

        for (int i = 0; i < 5; i++) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getAge, 99999);
            List<User> list = userService.list(queryWrapper);
        }

        return "success";
    }


    // 测试地址 http://localhost:8080/testLambda
    @RequestMapping(value = "testLambda")
    public String testLambda(){
        userService.lambda();
        userService.andOr();
        return "success";
    }


    // 测试地址 http://localhost:8080/test3Search
    @RequestMapping(value = "test3Search")
    public String test3Search(){
        userService.test3Search();
        return "success";
    }




    // 测试地址 http://localhost:8080/batchSave
    @RequestMapping(value = "batchSave")
    public String batchSave(){
        userService.batchSave();
        return "success";
    }
}
