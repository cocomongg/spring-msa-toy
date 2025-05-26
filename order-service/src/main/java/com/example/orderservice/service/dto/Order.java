package com.example.orderservice.service.dto;

import com.example.orderservice.model.OrderEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Order {
    private final String orderId;
    private final String productId;
    private final String userId;
    private final Integer quantity;
    private final Integer unitPrice;
    private final Integer totalPrice;
    private final LocalDateTime createdAt;

    public static Order from(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(orderEntity.getOrderId())
                .productId(orderEntity.getProductId())
                .userId(orderEntity.getUserId())
                .quantity(orderEntity.getQuantity())
                .unitPrice(orderEntity.getUnitPrice())
                .totalPrice(orderEntity.getTotalPrice())
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }
}
