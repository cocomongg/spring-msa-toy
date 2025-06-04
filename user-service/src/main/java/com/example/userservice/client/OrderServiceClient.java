package com.example.userservice.client;

import com.example.userservice.service.dto.GetOrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders")
    GetOrdersResponse getOrders(@PathVariable String userId);
}
