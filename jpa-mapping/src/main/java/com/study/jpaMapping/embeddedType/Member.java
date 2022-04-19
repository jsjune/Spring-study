package com.study.jpaMapping.embeddedType;

import com.study.jpaMapping.collectionPrac.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    // Period
    @Embedded
    private Period workPeriod;

    // Address
    @Embedded
    private Address homeAddress;
}
