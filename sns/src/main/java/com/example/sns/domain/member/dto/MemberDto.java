package com.example.sns.domain.member.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String nickname;
    private String email;
    private LocalDate birthday;
}
