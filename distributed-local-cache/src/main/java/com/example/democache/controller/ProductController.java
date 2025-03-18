package com.example.democache.controller;

import com.example.democache.entity.Products;
import com.example.democache.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Products> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Products saveProduct(@RequestBody Products products) {
        return productService.saveProduct(products);
    }

    @PutMapping
    public Products updateProduct(@RequestBody Products products) {
        return productService.updateProduct(products);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
