package com.example.orderservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderRequest {

    @Getter
    @NoArgsConstructor
    public static class CreateOrderRequest {
        private String productId;
        private Integer quantity;
        private Integer unitPrice;
    }
}
