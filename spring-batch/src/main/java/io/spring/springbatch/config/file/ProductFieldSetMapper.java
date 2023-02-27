package io.spring.springbatch.config.file;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ProductFieldSetMapper implements FieldSetMapper<Product> {
    @Override
    public Product mapFieldSet(FieldSet fieldSet) throws BindException {
        Product product = Product.builder()
                .id(fieldSet.readLong(0))
                .name(fieldSet.readString(1))
                .price(fieldSet.readBigDecimal(2))
                .build();
        return product;
    }
}
