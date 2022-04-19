package com.study.jpaMapping.embeddedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
