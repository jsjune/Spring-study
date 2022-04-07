package com.study.jpaMapping.model1vs1;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name="LOCKER_ID")
    private Locker locker;

}
