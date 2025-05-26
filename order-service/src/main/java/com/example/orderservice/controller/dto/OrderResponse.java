package com.example.orderservice.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class OrderResponse {

    @Getter
    @Builder
    public static class CommonOrderResponse {
        private final String orderId;
        private final String productId;
        private final String userId;
        private final Integer quantity;
        private final Integer unitPrice;
        private final Integer totalPrice;
        private final LocalDateTime createdAt;
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetOrdersResponse {
        private final List<CommonOrderResponse> orders;
    }
}
