package org.example.springmysqlelasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.example.springmysqlelasticsearch.entity.Product;
import org.example.springmysqlelasticsearch.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


}
