package com.study.jpaMapping.modelNvsM;

import com.study.jpaMapping.modelNvs1.Team;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

//    @ManyToMany
//    @JoinTable(name="MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
