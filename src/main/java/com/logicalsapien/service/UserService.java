package com.logicalsapien.service;

import com.logicalsapien.entity.User;

import java.util.List;

public interface UserService {

    User findUserByUserName(String username);

    User findUserByEmail(String email);

    User saveUser(User user);

    List<User> getAllUsers();

    User updateUser(String username, User user);

    void deleteGalaxy(String username);
}
