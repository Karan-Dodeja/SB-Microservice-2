package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.entity.User;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);
}
