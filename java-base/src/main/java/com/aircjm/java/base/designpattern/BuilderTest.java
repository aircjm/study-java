package com.aircjm.java.base.designpattern;

import cn.hutool.json.JSONUtil;
import com.aircjm.java.base.vo.UserParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuilderTest {




    public static void main(String[] args) {
        UserParam userParam = UserParam.builder().age(10).build();
        log.info(JSONUtil.toJsonStr(userParam));


        UserParam newUser = new UserParam();
        log.info(JSONUtil.toJsonStr(newUser));
    }

}
