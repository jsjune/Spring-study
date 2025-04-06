package org.example.chatservice.dtos;

import java.time.LocalDate;
import org.example.chatservice.entities.Gender;
import org.example.chatservice.entities.Member;

public record MemberDto(
    Long id,
    String email,
    String nickname,
    String name,
    String password,
    String confirmedPassword,
    Gender gender,
    String phoneNumber,
    LocalDate birthDay,
    String role
) {
    public static MemberDto from(Member member) {
        return new MemberDto(
            member.getId(),
            member.getEmail(),
            member.getNickname(),
            member.getName(),
            null,
            null,
            member.getGender(),
            member.getPhoneNumber(),
            member.getBirthDay(),
            member.getRole()
        );
    }

    public static Member to(MemberDto memberDto) {
        return Member.builder()
            .id(memberDto.id())
            .email(memberDto.email())
            .nickname(memberDto.nickname())
            .name(memberDto.name())
            .gender(memberDto.gender)
            .phoneNumber(memberDto.phoneNumber())
            .birthDay(memberDto.birthDay())
            .role(memberDto.role())
            .build();
    }
}
