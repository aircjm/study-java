package com.rimlook.mybatisplus.sqlite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rimlook.mybatisplus.sqlite.entity.User;

/**
 * @author nieqiuqiu
 */
public interface IUserService extends IService<User> {

    void lambda();


    void andOr();
}
