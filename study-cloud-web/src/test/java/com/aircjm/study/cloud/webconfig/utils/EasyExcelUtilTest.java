package com.aircjm.study.cloud.webconfig.utils;

import com.aircjm.study.cloud.webconfig.vo.UserEvo;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

class EasyExcelUtilTest {




    @Test
    void createFile() {
        List<UserEvo> userList = Lists.newArrayList();
        for (int i = 0; i < 5000; i++) {
            UserEvo user = new UserEvo();
            user.setName("姓名" + i);
            user.setAge(i);
            userList.add(user);
        }

        EasyExcelUtil.createFile("测试", UserEvo.class, userList);
    }
}