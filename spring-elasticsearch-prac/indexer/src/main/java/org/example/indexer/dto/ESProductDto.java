package org.example.indexer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ESProductDto {
    Long id;
    String asin;
    String title;
    String imgUrl;
    String productUrl;
    float stars;
    int reviews;
    BigDecimal price;
    BigDecimal listPrice;
    int categoryId;
    @JsonProperty("is_best_seller")
    boolean isBestSeller;
    int boughtInLastMonth;
    @JsonProperty("is_recommend_seller")
    boolean isRecommendSeller;
    Date createdAt;
    Date updatedAt;
}
