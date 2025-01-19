package org.example.indexer.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    String asin;
    String title;
    String imgUrl;
    String productUrl;
    float stars;
    int reviews;
    BigDecimal price;
    BigDecimal listPrice;
    int categoryId;
    boolean isBestSeller;
    int boughtInLastMonth;
}
