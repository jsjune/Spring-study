package org.example.searcher.service;

import lombok.RequiredArgsConstructor;
import org.example.searcher.entity.Category;
import org.example.searcher.repository.CategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Cacheable(cacheNames = "categories", key = "#id")
    @Scheduled(fixedRate = 43200000)
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
