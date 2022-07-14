package com.study.jpaMapping.embeddedType;

import com.study.jpaMapping.collectionPrac.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                    column=@Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name = "WORK_ZIP"))
    })
    private Address workAddress;
}
