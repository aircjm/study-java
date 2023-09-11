package com.aircjm.study.cloud.web.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class AopService {


    public void test() {
        System.out.println("hello");
    }


    public void test_2() {

        AopService aopService = (AopService) AopContext.currentProxy();
        aopService.test();

        System.out.println("world");
    }

}
