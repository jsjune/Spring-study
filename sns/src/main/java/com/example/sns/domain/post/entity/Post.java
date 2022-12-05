package com.example.sns.domain.post.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
    @Index(name = "i_member_id", columnList = "memberId"),
    @Index(name = "i_created_date", columnList = "createdDate"),
    @Index(name = "i_memberId_createdDate", columnList = "memberId, createdDate")
})
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;

    private Long memberId;
    private String contents;
    private LocalDate createdDate;
    private LocalDateTime createdAt;

//    @Builder
//    public Post( Member member, String contents, LocalDate createdDate,
//        LocalDateTime createdAt) {
//        this.member = member;
//        this.contents = contents;
//        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
//        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
//    }

    @Builder
    public Post(Long memberId, String contents, LocalDate createdDate,
        LocalDateTime createdAt) {
        this.memberId = memberId;
        this.contents = contents;
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
