package com.study.jpaMapping.model1vsN;

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

//    @ManyToOne // 읽기 전용 맵핑
//    @JoinColumn(name="TEAM_ID",insertable = false,updatable = false)
//    private Team team;

}
