package com.example.sns.domain.member.dto;

import com.example.sns.domain.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberNicknameHistoryDto {
    private Long id;
    private Long memberId;
    private String nickname;
    private LocalDateTime createdAt;
}
