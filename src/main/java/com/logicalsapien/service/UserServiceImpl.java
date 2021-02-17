package com.logicalsapien.service;

import com.logicalsapien.entity.Role;
import com.logicalsapien.entity.User;
import com.logicalsapien.repository.RoleRepository;
import com.logicalsapien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByUserName(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            return userRepository.findById(username).get();
        } else {
            return null;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // default disabled, admin to enable
        user.setEnabled(false);
        // default read permission, admin to update
        Role userRole = roleRepository.findByRole("READ");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String username, User user) {
        // fetch user
        User userFromDB = findUserByUserName(username);
        if (Objects.nonNull(user.getPassword()) && user.getPassword().length() > 0) {
            userFromDB.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userFromDB.setEmail(user.getEmail());
        userFromDB.setEnabled(user.isEnabled());
        userFromDB.setRoles(user.getRoles());
        return userRepository.save(userFromDB);
    }

    @Override
    public void deleteGalaxy(String username) {
        userRepository.deleteById(username);
    }

}
