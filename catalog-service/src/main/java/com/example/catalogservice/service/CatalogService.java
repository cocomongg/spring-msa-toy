package com.example.catalogservice.service;

import com.example.catalogservice.model.CatalogEntity;
import com.example.catalogservice.repository.CatalogRepository;
import com.example.catalogservice.service.dto.Catalog;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    public List<Catalog> getCatalogs() {
        List<CatalogEntity> catalogEntities = catalogRepository.findAll();
        List<Catalog> catalogs = new ArrayList<>();

        catalogEntities.forEach(catalogEntity -> catalogs.add(Catalog.from(catalogEntity)));
        return catalogs;
    }
}
