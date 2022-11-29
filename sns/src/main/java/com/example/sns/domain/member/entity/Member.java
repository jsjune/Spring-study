package com.example.sns.domain.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Size(max = 10)
    private String nickname;
    private String email;
    private LocalDate birthday;
    private LocalDateTime createdAt;

    @Builder
    public Member(String nickname, String email, LocalDate birthday,
        LocalDateTime createdAt) {
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public void changeNickname(String nickname) {
        Assert.isTrue(nickname.length() <= 10, "최대 길이를 초과했습니다.");
        this.nickname = nickname;
    }
}
