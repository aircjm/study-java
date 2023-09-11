package com.aircjm.study.cloud.web.service.impl;

import com.aircjm.study.cloud.web.service.AopService;
import com.aircjm.study.cloud.web.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class TestServiceImpl implements TestService {


    @Resource
    private AopService aopService;

    @Override
    @Scheduled(cron = "*/5 * * * * *")
    public void testCronSchedule() {
        log.info("测试单机定时任务");
    }

    @Override
    public void testAop() {
        log.info("测试Aop");
        aopService.test_2();;
    }
}
