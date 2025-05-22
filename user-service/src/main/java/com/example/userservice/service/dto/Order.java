package com.example.userservice.service.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Order {
    private final String orderId;
    private final String productId;
    private final Integer qty;
    private final Integer unitPrice;
    private final Integer totalPrice;
    private final LocalDateTime createdAt;
}
