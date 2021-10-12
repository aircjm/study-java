package com.aircjm.study.cloud.postgis.service.impl;

import com.aircjm.study.cloud.postgis.domain.User;
import com.aircjm.study.cloud.postgis.mapper.UserMappper;
import com.aircjm.study.cloud.postgis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMappper userMappper;

    @Override
    public void addUser(User user) {
        userMappper.insert(user);
    }
}
