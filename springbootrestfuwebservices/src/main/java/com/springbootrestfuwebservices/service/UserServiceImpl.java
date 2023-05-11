package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.exception.ResourceNotFoundException;
import com.springbootrestfuwebservices.mapper.UserMapper;
import com.springbootrestfuwebservices.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // User user = UserMapper.mapToUser(userDto);

        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        // UserDto savedUserDto = UserMapper.mappToUserDto(savedUser);

        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id", userId)
        );

        // return UserMapper.mappToUserDto(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mappToUserDto)
        //      .collect(Collectors.toList());

        return users.stream().map((user)->modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",user.getId())
        );
        findUser.setFirstName(findUser.getFirstName());
        findUser.setLastName(findUser.getLastName());
        findUser.setEmail(findUser.getEmail());
        User updateExistUser = userRepository.save(findUser);
        // return UserMapper.mappToUserDto(updateExistUser);
        return modelMapper.map(updateExistUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User deleteUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userId);
    }

}