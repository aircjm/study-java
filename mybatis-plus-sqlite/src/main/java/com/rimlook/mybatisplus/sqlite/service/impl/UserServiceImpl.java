package com.rimlook.mybatisplus.sqlite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rimlook.mybatisplus.sqlite.entity.User;
import com.rimlook.mybatisplus.sqlite.mapper.UserMapper;
import com.rimlook.mybatisplus.sqlite.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author nieqiuqiu
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
