package com.example.catalogsservice.repository;

import com.example.catalogsservice.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
