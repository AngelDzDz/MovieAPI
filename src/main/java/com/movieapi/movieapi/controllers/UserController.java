package com.movieapi.movieapi.controllers;


import com.movieapi.movieapi.converters.UserConverter;
import com.movieapi.movieapi.dtos.*;
import com.movieapi.movieapi.entities.User;
import com.movieapi.movieapi.services.UserService;
import com.movieapi.movieapi.utils.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "User")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @Operation(summary="Retrieves all the users",description="Returns the list of created users")
    @GetMapping(value = "/users")
    public ResponseEntity<WrapperResponse<List<UserDTO>>> findAll() {
        List<User> userList = userService.findAll();
        return new WrapperResponse<>(true, "success", userConverter.fromEntity(userList)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Retrieve a user",description="Returns a user based on it's id")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<WrapperResponse<UserDTO>> findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new WrapperResponse<>(true, "success", userConverter.fromEntity(user)).createResponse(HttpStatus.OK);

    }

    @Operation(summary="Register a user",description="Register a user on the database")
    @PostMapping(value = "/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request) {
        User user = userService.createUser(userConverter.signUp(request));
        return new WrapperResponse<>(true,"success",userConverter.fromEntity(user)).createResponse(HttpStatus.OK);
    }

    @Operation(summary="Allows a user to login on the API",description="Allows a user to login and retrieves a token")
    @PostMapping(value = "/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
        return new WrapperResponse<>(true,"success", userService.login(loginRequest)).createResponse(HttpStatus.OK);
    }
}


