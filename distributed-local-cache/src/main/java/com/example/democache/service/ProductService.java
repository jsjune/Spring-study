package com.example.democache.service;

import com.example.democache.entity.Products;
import com.example.democache.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CachePublisher cachePublisher;

    @Cacheable(value = "myCache", key = "#id.toString()")
    public Products getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "myCache", key = "'allProducts'")
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @CacheEvict(value = "myCache", key = "'allProducts'")
    public Products saveProduct(Products products) {
        Products saved = productRepository.save(products);
        cachePublisher.publishInvalidateMessage("allProducts"); // 전체 목록 캐시 삭제 요청
        return saved;
    }

    @Caching(evict = {
            @CacheEvict(value = "myCache", key = "'allProducts'"), // 전체 목록 초기화
            @CacheEvict(value = "myCache", key = "#products.getId().toString()") // 해당 ID 초기화
    })
    public Products updateProduct(Products products) {
        Products updated = productRepository.save(products);
        String key = "allProducts-" + products.getId().toString();
        cachePublisher.publishInvalidateMessage(key); // 전체 목록 삭제
        return updated;
    }

    @Caching(evict = {
            @CacheEvict(value = "myCache", key = "'allProducts'", allEntries = true),
            @CacheEvict(value = "myCache", key = "#id.toString()")
    })
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        String key = "allProducts-" + id.toString();
        cachePublisher.publishInvalidateMessage(key); // 단건 조회 삭제
    }
}
