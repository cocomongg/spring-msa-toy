package com.example.userservice.controller;

import com.example.userservice.controller.dto.Greeting;
import com.example.userservice.controller.dto.UserRequest;
import com.example.userservice.controller.dto.UserResponse.CreateUserResponse;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final Greeting greeting;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "User Service is up and running!";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public CreateUserResponse createUser(@Valid @RequestBody UserRequest.CreateUserRequest request) {
        CreateUserCommand createUserCommand = userMapper.toCreateUserCommand(request);
        User user = userService.createUser(createUserCommand);

        return userMapper.toCreateUserResponse(user);
    }
}
