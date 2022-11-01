package com.example.catalogsservice.service;

import com.example.catalogsservice.domain.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
