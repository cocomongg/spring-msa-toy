package com.example.userservice.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateUserCommand {
    private final String email;
    private final String name;
    private final String password;
}
