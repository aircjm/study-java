package com.aircjm.study.cloud.web.service.impl;

import com.aircjm.study.cloud.web.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void test() {
        System.out.println("test");
    }
}
