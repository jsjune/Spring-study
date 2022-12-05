package com.example.sns.domain.post.service;

import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostWriteService {

    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        Post post = Post.builder()
            .memberId(command.memberId())
            .contents(command.contents())
            .build();
        return postRepository.save(post).getId();
    }
}
