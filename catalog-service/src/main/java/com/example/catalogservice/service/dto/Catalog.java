package com.example.catalogservice.service.dto;

import com.example.catalogservice.model.CatalogEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Catalog {
    private final String productId;
    private final String productName;
    private final Integer stock;
    private final Integer unitPrice;
    private final LocalDateTime createdAt;

    public static Catalog from(CatalogEntity catalogEntity) {
        return Catalog.builder()
                .productId(catalogEntity.getProductId())
                .productName(catalogEntity.getProductName())
                .stock(catalogEntity.getStock())
                .unitPrice(catalogEntity.getUnitPrice())
                .createdAt(catalogEntity.getCreatedAt())
                .build();
    }
}
