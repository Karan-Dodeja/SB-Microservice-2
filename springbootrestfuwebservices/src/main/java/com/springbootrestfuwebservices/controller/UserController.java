package com.springbootrestfuwebservices.controller;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;
import com.springbootrestfuwebservices.exception.ErrorDetails;
import com.springbootrestfuwebservices.exception.ResourceNotFoundException;
import com.springbootrestfuwebservices.service.UserService;
import com.springbootrestfuwebservices.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST API for User Resource",
        description = "CRUD REST API = Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(
            summary = "create user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }
    @Operation(
            summary = "get single user data"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUSerById(@PathVariable("id") Long userId) {
        UserDto userDetails = userService.getUserById(userId);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @Operation(
            summary = "get all user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Operation(
            summary = "update single user data"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 UPDATED"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updatUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUsers = userService.updateUser(user);
        return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
    }

    @Operation(
            summary = "delete single user data"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")  Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

}
