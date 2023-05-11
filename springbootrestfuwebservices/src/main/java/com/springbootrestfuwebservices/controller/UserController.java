package com.springbootrestfuwebservices.controller;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.exception.ErrorDetails;
import com.springbootrestfuwebservices.exception.ResourceNotFoundException;
import com.springbootrestfuwebservices.service.UserService;
import com.springbootrestfuwebservices.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUSerById(@PathVariable("id") Long userId) {
        UserDto userDetails = userService.getUserById(userId);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<UserDto> updatUser(@PathVariable("id") Long userId,@RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUsers = userService.updateUser(user);
        return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")  Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted!");
    }

}
