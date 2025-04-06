package org.example.springmysqlelasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDto(
    Long id,
    String name,
    String description,
    BigDecimal price,
    @JsonProperty("created_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime createdAt
) {
}
