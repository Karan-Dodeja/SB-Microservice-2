package com.springbootrestfuwebservices.service;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.exception.EmailAlreadyExistsException;
import com.springbootrestfuwebservices.exception.ResourceNotFoundException;

import com.springbootrestfuwebservices.mapper.AutoUserMapper;
import com.springbootrestfuwebservices.mapper.UserMapper;
import com.springbootrestfuwebservices.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

        //User user = modelMapper.map(userDto, User.class);

        //User savedUser = userRepository.save(user);

        // UserDto savedUserDto = UserMapper.mappToUserDto(savedUser);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mappToUserDto(savedUser);

        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id", userId)
        );

        // return UserMapper.mappToUserDto(user);
        return AutoUserMapper.MAPPER.mappToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mappToUserDto)
        //      .collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.MAPPER.mappToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updateExistUser = userRepository.save(existingUser);
        // return UserMapper.mappToUserDto(updateExistUser);
        return AutoUserMapper.MAPPER.mappToUserDto(updateExistUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User deleteUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userId);
    }

}