package com.logicalsapien.service;

import com.logicalsapien.entity.User;

import java.util.List;

public interface UserService {

    User findUserByUserName(String username);

    User findUserByEmail(String email);

    void saveUser(User user);

    List<User> getAllUsers();
}
