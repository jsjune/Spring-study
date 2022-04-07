package com.study.jpaMapping.modelNvs1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "team")
//    private List<Member> members = new ArrayList<>();
}
