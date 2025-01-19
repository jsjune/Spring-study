package org.example.searcher.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.searcher.dto.ProductDto;
import org.example.searcher.service.CategoryService;
import org.example.searcher.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearcherController {
    private final SearchService searchService;
    private final CategoryService categoryService;

    @GetMapping("/api/products/search")
    public List<ProductDto> search(
        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
        @RequestParam(value = "query") String query,
        @RequestParam(value = "last_asin", required = false) String lastAsin,
        @RequestParam(value = "last_score", required = false) Double lastScore
    ) throws IOException {
        List<ProductDto> result = searchService.searchProducts(query, size, lastScore, lastAsin);

        fetchCategoryName(result);

        return result;
    }

    private void fetchCategoryName(List<ProductDto> productDtos) {
        productDtos.forEach(productDto -> {
            productDto.setCategory_name(categoryService.getCategory(productDto.getCategory_id()).getCategoryName());
        });
    }
}
