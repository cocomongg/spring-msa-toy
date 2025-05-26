package com.example.orderservice.mapper;

import com.example.orderservice.controller.dto.OrderRequest.CreateOrderRequest;
import com.example.orderservice.controller.dto.OrderResponse;
import com.example.orderservice.controller.dto.OrderResponse.CommonOrderResponse;
import com.example.orderservice.controller.dto.OrderResponse.GetOrdersResponse;
import com.example.orderservice.service.dto.CreateOrderCommand;
import com.example.orderservice.service.dto.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public CreateOrderCommand toCreateOrderCommand(CreateOrderRequest request, String userId) {
        return CreateOrderCommand.builder()
                .userId(userId)
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .build();
    }

    public OrderResponse.CommonOrderResponse toCreateOrderResponse(Order order) {
        return OrderResponse.CommonOrderResponse.builder()
                .orderId(order.getOrderId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .userId(order.getUserId())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public OrderResponse.GetOrdersResponse toGetOrdersResponse(List<Order> orders) {
        List<CommonOrderResponse> orderResponses = new ArrayList<>();
        orders.forEach(order -> {
            CommonOrderResponse commonOrderResponse = this.toCreateOrderResponse(order);
            orderResponses.add(commonOrderResponse);
        });

        return new GetOrdersResponse(orderResponses);
    }
}
