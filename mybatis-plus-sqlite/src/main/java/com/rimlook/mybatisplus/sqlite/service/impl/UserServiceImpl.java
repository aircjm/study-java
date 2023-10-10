package com.rimlook.mybatisplus.sqlite.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rimlook.mybatisplus.sqlite.entity.User;
import com.rimlook.mybatisplus.sqlite.mapper.UserMapper;
import com.rimlook.mybatisplus.sqlite.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nieqiuqiu
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public  void lambda() {
        // SELECT age FROM sys_user
        List<User> list = lambdaQuery().select(User::getAge).list();
        log.info("list is: {}", list);
        log.info("-------------------------------\n\n\n");


        //  SELECT id,name,age,email FROM sys_user WHERE (age = 20)
        List<User> conditionList = lambdaQuery().eq(User::getAge, 20).list();
        log.info("conditionList is: {}", conditionList);
        log.info("-------------------------------\n\n\n");


        // one的时候如果有多条记录会直接报错 所以一定要保障最多只有一条记录返回
        // SELECT id,name,age,email FROM sys_user limit 1
        User one = lambdaQuery().last("limit 1").one();
        log.info("one is: {}", one);
        log.info("-------------------------------\n\n\n");


        // todo 没有做到分页 需要调研一下为什么
        // 1. 写法问题
        // 2. sqlite语法问题
        Page<User> pageResult = lambdaQuery().eq(User::getAge, 20).page(Page.of(1, 10));
        log.info("pageResult is: {}", pageResult);
        log.info("-------------------------------\n\n\n");
    }

    @Override
    public void andOr() {
        LambdaQueryChainWrapper<User> qw = lambdaQuery().eq(User::getIsDelete, 0);
        qw.and(item -> item.eq(User::getUserStatus, 0)
                .or().eq(User::getUserStatus, 1)
                .or().eq(User::getUserStatus, 2)
        );

//        SELECT id,name,age,email,user_status,is_delete FROM sys_user WHERE (is_delete = 0 AND (user_status = 0 OR user_status = 1 OR user_status = 2))
        List<User> list = qw.list();
        log.info("list size is: {}", list);

    }

    @Override
    public void test3Search() {

        for (int i = 0; i < 5; i++) {
            LambdaQueryChainWrapper<User> qw = lambdaQuery().eq(User::getIsDelete, 0);
            qw.and(item -> item.eq(User::getUserStatus, 0)
                    .or().eq(User::getUserStatus, 1)
                    .or().eq(User::getUserStatus, 2)
            );

//        SELECT id,name,age,email,user_status,is_delete FROM sys_user WHERE (is_delete = 0 AND (user_status = 0 OR user_status = 1 OR user_status = 2))
            List<User> list = qw.list();
            log.info("list size is: {}", list.size());
        }
    }
}
