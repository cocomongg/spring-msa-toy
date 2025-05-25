package com.example.catalogservice.mapper;

import com.example.catalogservice.controller.dto.CatalogResponse;
import com.example.catalogservice.controller.dto.CatalogResponse.CatalogItem;
import com.example.catalogservice.controller.dto.CatalogResponse.GetCatalogsResponse;
import com.example.catalogservice.service.dto.Catalog;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CatalogMapper {

    public CatalogResponse.GetCatalogsResponse toGetCatalogsResponse(List<Catalog> catalog) {
        List<CatalogResponse.CatalogItem> catalogItems = new ArrayList<>();
        catalog.forEach(item -> {
            CatalogItem catalogItem = CatalogItem.builder()
                    .productId(item.getProductId())
                    .productName(item.getProductName())
                    .unitPrice(item.getUnitPrice())
                    .stock(item.getStock())
                    .createdAt(item.getCreatedAt())
                    .build();
            catalogItems.add(catalogItem);
        });

        return new GetCatalogsResponse(catalogItems);
    }
}
