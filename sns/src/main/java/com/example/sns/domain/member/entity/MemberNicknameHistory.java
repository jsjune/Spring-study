package com.example.sns.domain.member.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNicknameHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NICKNAME_HISTORY_ID")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
    private Long memberId;

    private String nickname;
    private LocalDateTime createdAt;

//    @Builder
//    public MemberNicknameHistory(Member member,String nickname, LocalDateTime createdAt) {
//        this.member = member;
//        this.nickname = nickname;
//        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
//    }

    @Builder
    public MemberNicknameHistory(Long memberId, String nickname, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
