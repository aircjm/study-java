package com.aircjm.study.cloud.web.test;

// UserRepository 接口
public interface UserRepository {
    User findById(String id);
    void save(User user);
}

