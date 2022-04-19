package com.study.jpaMapping.collectionPrac;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
