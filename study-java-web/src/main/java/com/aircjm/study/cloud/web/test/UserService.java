package com.aircjm.study.cloud.web.test;



// UserService 类
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String id) {
        return userRepository.findById(id);
    }

    public void createUser(String id, String name) {
        User newUser = new User(id, name);
        userRepository.save(newUser);
    }
}