package com.logicalsapien.service;

import com.logicalsapien.entity.User;

public interface UserService {

    User findUserByUserName(String username);

    User findUserByEmail(String email);

    void saveUser(User user);
}
