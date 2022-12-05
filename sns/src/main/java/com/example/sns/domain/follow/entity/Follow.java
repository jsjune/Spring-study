package com.example.sns.domain.follow.entity;

import com.example.sns.domain.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLLOW_ID")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "FROM_MEMBER_ID")
//    private Member fromMember;
//    @ManyToOne
//    @JoinColumn(name = "TO_MEMBER_ID")
//    private Member toMember;
    private Long fromMemberId;
    private Long toMemberId;

    private LocalDateTime createdAt;

//    @Builder
//    public Follow(Member fromMember, Member toMember, LocalDateTime createdAt) {
//        this.fromMember = fromMember;
//        this.toMember = toMember;
//        this.createdAt = createdAt;
//    }

    @Builder
    public Follow(Long fromMemberId, Long toMemberId, LocalDateTime createdAt) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

}
