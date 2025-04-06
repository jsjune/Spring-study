package org.example.springmysqlelasticsearch.repository;

import org.example.springmysqlelasticsearch.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
