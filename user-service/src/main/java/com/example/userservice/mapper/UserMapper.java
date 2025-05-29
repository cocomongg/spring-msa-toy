package com.example.userservice.mapper;

import com.example.userservice.controller.dto.UserRequest;
import com.example.userservice.controller.dto.UserResponse;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.UserInfo;
import java.util.ArrayList;
import java.util.List;
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

    public UserResponse.CreateUserResponse toCreateUserResponse(UserInfo userInfo) {
        return UserResponse.CreateUserResponse.builder()
                .userId(userInfo.getUserId())
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .build();
    }

    public UserResponse.GetUserResponse toGetUserResponse(UserInfo userInfo) {
        List<UserResponse.OrderItem> orderItems = new ArrayList<>();
        userInfo.getOrders().forEach(order -> {
            UserResponse.OrderItem orderItem = UserResponse.OrderItem.builder()
                    .orderId(order.getOrderId())
                    .productId(order.getProductId())
                    .qty(order.getQty())
                    .unitPrice(order.getUnitPrice())
                    .totalPrice(order.getTotalPrice())
                    .createdAt(order.getCreatedAt())
                    .build();
            orderItems.add(orderItem);
        });

        return UserResponse.GetUserResponse.builder()
                .userId(userInfo.getUserId())
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .orders(orderItems)
                .build();
    }

    public UserResponse.GetUserListResponse toGetUserListResponse(List<UserInfo> userInfos) {
        List<UserResponse.CommonUserResponse> responses  = new ArrayList<>();
        userInfos.forEach(user -> {
            UserResponse.CommonUserResponse response = UserResponse.CommonUserResponse.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
            responses.add(response);
        });

        return new UserResponse.GetUserListResponse(responses);
    }
}
