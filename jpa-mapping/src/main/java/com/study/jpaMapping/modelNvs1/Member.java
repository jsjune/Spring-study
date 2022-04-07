package com.study.jpaMapping.modelNvs1;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @ManyToOne // 연관관계 주인
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
