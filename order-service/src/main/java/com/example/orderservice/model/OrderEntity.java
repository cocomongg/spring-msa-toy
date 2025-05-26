package com.example.orderservice.model;

import com.example.orderservice.service.dto.CreateOrderCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public OrderEntity(CreateOrderCommand command, String orderId) {
        this.orderId = orderId;
        this.productId = command.getProductId();
        this.quantity = command.getQuantity();
        this.unitPrice = command.getUnitPrice();
        this.totalPrice = this.quantity * this.unitPrice;
        this.userId = command.getUserId();
        this.createdAt = LocalDateTime.now();
    }
}
