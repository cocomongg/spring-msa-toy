package com.example.userservice.service.dto;

import com.example.userservice.model.UserEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserInfo {
    private final String userId;
    private final String email;
    private final String name;
    private final LocalDateTime createdAt;
    private final List<Order> orders;

    public static UserInfo from(UserEntity userEntity, List<Order> orders) {
        return UserInfo.builder()
            .userId(userEntity.getUserId())
            .email(userEntity.getEmail())
            .name(userEntity.getName())
            .createdAt(userEntity.getCreatedAt())
            .orders(orders)
            .build();
    }

    public static UserInfo from(UserEntity userEntity) {
        return UserInfo.builder()
            .userId(userEntity.getUserId())
            .email(userEntity.getEmail())
            .name(userEntity.getName())
            .createdAt(userEntity.getCreatedAt())
            .orders(new ArrayList<>())
            .build();
    }
}
