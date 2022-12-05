package com.example.sns.domain.post.entity;

import java.time.LocalDateTime;
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
public class PostLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private Long postId;
    private LocalDateTime createdAt;

    @Builder
    public PostLike(Long memberId, Long postId, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.postId = postId;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
