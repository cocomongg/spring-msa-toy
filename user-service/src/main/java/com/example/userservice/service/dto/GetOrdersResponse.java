package com.example.userservice.service.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetOrdersResponse {
    private final List<Order> orders;
}
