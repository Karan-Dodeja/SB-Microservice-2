package com.springbootrestfuwebservices.controller;

import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.service.UserService;
import com.springbootrestfuwebservices.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUSerById(@PathVariable("id") Long userId) {
        User userDetails = userService.getUserById(userId);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<User> updatUser(@PathVariable("id") Long userId,@RequestBody User user){
        user.setId(userId);
        User updatedUsers = userService.updateUser(user);
        return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
    }



}
