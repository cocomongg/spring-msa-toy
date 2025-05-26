package com.example.orderservice.service;

import com.example.orderservice.model.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.dto.CreateOrderCommand;
import com.example.orderservice.service.dto.Order;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(CreateOrderCommand command) {
        OrderEntity orderEntity = new OrderEntity(command, UUID.randomUUID().toString());
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        return Order.from(savedOrderEntity);
    }

    @Transactional(readOnly = true)
    public Order getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with orderId: " + orderId));

        return Order.from(orderEntity);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByUserId(String userId) {
        List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);
        List<Order> orders = new ArrayList<>();

        orderEntities.forEach(orderEntity -> orders.add(Order.from(orderEntity)));

        return orders;
    }
}
