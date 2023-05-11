package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}