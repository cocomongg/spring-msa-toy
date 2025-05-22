package com.example.userservice.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

public class UserResponse {

    @Getter
    @SuperBuilder
    public static class CommonUserResponse {
        private final String userId;
        private final String email;
        private final String name;
    }

    @Getter
    @SuperBuilder
    public static class CreateUserResponse extends CommonUserResponse {

    }

    @Getter
    @SuperBuilder
    public static class GetUserResponse extends CommonUserResponse {
        private final List<OrderItem> orders;
    }

    @Getter
    @Builder
    public static class OrderItem {
        private final String orderId;
        private final String productId;
        private final Integer qty;
        private final Integer unitPrice;
        private final Integer totalPrice;
        private final LocalDateTime createdAt;
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetUserListResponse {
        private final List<CommonUserResponse> users;
    }
}
