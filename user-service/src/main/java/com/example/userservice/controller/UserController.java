package com.example.userservice.controller;

import com.example.userservice.controller.dto.Greeting;
import com.example.userservice.controller.dto.UserRequest;
import com.example.userservice.controller.dto.UserResponse.CreateUserResponse;
import com.example.userservice.controller.dto.UserResponse.GetUserListResponse;
import com.example.userservice.controller.dto.UserResponse.GetUserResponse;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.UserInfo;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final Environment env;
    private final Greeting greeting;

    @GetMapping("/health-check")
    public String healthCheck() {
        return String.format("It's Working in User Service on Port %s",
            env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody UserRequest.CreateUserRequest request) {
        CreateUserCommand createUserCommand = userMapper.toCreateUserCommand(request);
        UserInfo userInfo = userService.createUser(createUserCommand);
        CreateUserResponse createUserResponse = userMapper.toCreateUserResponse(userInfo);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(createUserResponse);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable String userId) {
        UserInfo userInfo = userService.getUserByUserId(userId);
        GetUserResponse getUserResponse = userMapper.toGetUserResponse(userInfo);

        return ResponseEntity.status(HttpStatus.OK)
            .body(getUserResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<GetUserListResponse> getUsers() {
        List<UserInfo> userInfos = userService.getUsers();
        GetUserListResponse getUserListResponse = userMapper.toGetUserListResponse(userInfos);

        return ResponseEntity.status(HttpStatus.OK)
            .body(getUserListResponse);
    }
}
