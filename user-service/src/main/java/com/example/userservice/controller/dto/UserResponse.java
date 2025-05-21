package com.example.userservice.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserResponse {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class CreateUserResponse {
        private final String userId;
        private final String email;
        private final String name;
    }

}
