package com.example.orderservice.repository;

import com.example.orderservice.model.OrderEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByOrderId(String orderId);
    List<OrderEntity> findByUserId(String userId);
}
