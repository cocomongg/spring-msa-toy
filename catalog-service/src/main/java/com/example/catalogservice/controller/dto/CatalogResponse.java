package com.example.catalogservice.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CatalogResponse {

    @Getter
    @RequiredArgsConstructor
    public static class GetCatalogsResponse {
        private final List<CatalogItem> catalogs;
    }

    @Getter
    @Builder
    public static class CatalogItem {
        private final String productId;
        private final String productName;
        private final Integer unitPrice;
        private final Integer stock;
        private final LocalDateTime createdAt;
    }
}
