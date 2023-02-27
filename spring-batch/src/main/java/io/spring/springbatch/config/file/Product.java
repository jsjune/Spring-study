package io.spring.springbatch.config.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;

    @Builder
    public Product(Long id,String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
