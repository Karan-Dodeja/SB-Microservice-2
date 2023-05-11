package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
}
