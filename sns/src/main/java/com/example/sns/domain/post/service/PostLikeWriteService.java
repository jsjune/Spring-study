package com.example.sns.domain.post.service;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {

    private final PostLikeRepository postLikeRepository;

}
