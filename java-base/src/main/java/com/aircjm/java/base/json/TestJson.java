package com.aircjm.java.base.json;

import cn.hutool.json.JSONUtil;

public class TestJson {


    public static void main(String[] args) {
        TestJsonVO testJsonVO = new TestJsonVO();

        testJsonVO.setClevel("1");
        testJsonVO.setALevel("1");

        System.out.println(JSONUtil.toJsonStr(JSONUtil.toBean(JSONUtil.toJsonStr(testJsonVO), TestJsonVO.class)));
    }
}
