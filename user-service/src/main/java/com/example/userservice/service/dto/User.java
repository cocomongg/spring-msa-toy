package com.example.userservice.service.dto;

import com.example.userservice.model.UserEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class User {
    private final String userId;
    private final String email;
    private final String name;
    private final LocalDateTime createdAt;

    public static User from(UserEntity userEntity) {
        return User.builder()
            .userId(userEntity.getUserId())
            .email(userEntity.getEmail())
            .name(userEntity.getName())
            .createdAt(userEntity.getCreatedAt())
            .build();
    }
}
