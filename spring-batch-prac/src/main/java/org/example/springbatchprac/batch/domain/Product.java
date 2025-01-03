package org.example.springbatchprac.batch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Product {
    @Id
    private Long id;
    private String name;
    private int price;
    private String type;

    public static Product of(Long id, String name, int price, String type) {
        return new Product(id, name, price, type);
    }

    public static Product from(ProductVO productVO) {
        return Product.builder()
            .id(productVO.id())
            .name(productVO.name())
            .price(productVO.price())
            .type(productVO.type())
            .build();
    }

}
