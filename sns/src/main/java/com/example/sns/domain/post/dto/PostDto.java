package com.example.sns.domain.post.dto;

import com.example.sns.domain.post.entity.Post;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Long memberId;
    private String contents;
    private LocalDate createdDate;
    private LocalDateTime createdAt;

    public PostDto(Post post) {
        this.memberId = post.getMemberId();
        this.contents = post.getContents();
        this.createdDate = post.getCreatedDate();
        this.createdAt = post.getCreatedAt();
    }
}
