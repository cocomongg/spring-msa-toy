package com.example.orderservice.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class CreateOrderCommand {
    private final String productId;
    private final Integer quantity;
    private final Integer unitPrice;
    private final String userId;
}
