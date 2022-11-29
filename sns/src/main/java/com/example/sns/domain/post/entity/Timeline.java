package com.example.sns.domain.post.entity;

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
public class Timeline {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMELINE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    private LocalDateTime createdAt;

    @Builder
    public Timeline(Member member, Post post, LocalDateTime createdAt) {
        this.member = member;
        this.post = post;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
