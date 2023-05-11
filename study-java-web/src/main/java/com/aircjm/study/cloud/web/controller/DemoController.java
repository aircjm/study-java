package com.aircjm.study.cloud.web.controller;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.aircjm.study.cloud.web.exceptions.Assert;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    public static void main(String[] args) {

        try {
            Assert.requireNonNull(null, "空指针异常");
        } catch (Exception e) {
            System.out.println(ExceptionUtil.stacktraceToString(e));
        }
    }

}
