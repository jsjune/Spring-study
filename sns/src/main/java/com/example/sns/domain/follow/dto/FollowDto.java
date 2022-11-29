package com.example.sns.domain.follow.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowDto {
    private Long id;
    private Long fromMemberId;
    private Long tomMemberId;
    private LocalDateTime createdAt;
}
