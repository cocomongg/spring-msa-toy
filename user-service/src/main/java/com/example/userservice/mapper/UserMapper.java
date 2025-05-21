package com.example.userservice.mapper;

import com.example.userservice.controller.dto.UserRequest;
import com.example.userservice.controller.dto.UserResponse;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public CreateUserCommand toCreateUserCommand(UserRequest.CreateUserRequest request) {
        return CreateUserCommand.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build();
    }

    public UserResponse.CreateUserResponse toCreateUserResponse(User user) {
        return UserResponse.CreateUserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
