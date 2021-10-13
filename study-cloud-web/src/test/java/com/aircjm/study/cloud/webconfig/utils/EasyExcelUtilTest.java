package com.aircjm.study.cloud.webconfig.utils;

import com.aircjm.study.cloud.webconfig.vo.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EasyExcelUtilTest {




    @Test
    void createFile() {
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 5000; i++) {
            User user = new User();
            user.setName("姓名" + i);
            user.setAge(i);
            userList.add(user);
        }

        EasyExcelUtil.createFile("测试", User.class, userList);
    }
}