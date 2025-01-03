package org.example.springbatchprac.batch.domain;

public record ProductVO(
    Long id,
    String name,
    int price,
    String type
) {

    public static ProductVO from(Product product) {
        return new ProductVO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getType()
        );
    }
}
