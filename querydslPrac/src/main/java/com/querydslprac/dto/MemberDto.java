package com.querydslprac.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.querydslprac.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private String username;
    private int age;

    @QueryProjection
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }

//    public MemberDto(Member member) {
//        this.username = member.getUsername();
//        this.age=member.getAge();
//    }
}
