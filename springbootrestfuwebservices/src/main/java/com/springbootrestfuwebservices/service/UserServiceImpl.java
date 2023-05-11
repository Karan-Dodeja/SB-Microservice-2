package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.mapper.UserMapper;
import com.springbootrestfuwebservices.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = UserMapper.mappToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> singleUser = userRepository.findById(userId);
        User user =  singleUser.get();
        return UserMapper.mappToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mappToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User findUser = userRepository.findById(user.getId()).get();
        findUser.setFirstName(findUser.getFirstName());
        findUser.setLastName(findUser.getLastName());
        findUser.setEmail(findUser.getEmail());
        User updateExistUser = userRepository.save(findUser);
        return UserMapper.mappToUserDto(updateExistUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}