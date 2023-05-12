package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
}
