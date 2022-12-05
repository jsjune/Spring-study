package com.example.sns.domain.post.service;

import com.example.sns.domain.post.dto.DailyPostCount;
import com.example.sns.domain.post.dto.DailyPostCountRequest;
import com.example.sns.domain.post.dto.PostDto;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.repository.PostLikeRepository;
import com.example.sns.domain.post.repository.PostRepository;
import com.example.sns.util.CursorRequest;
import com.example.sns.util.PageCursor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostReadService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        return postRepository.groupByCreateDate(request);
    }

    public Page<PostDto> getPosts(Long memberId, Pageable pageable) {
//        Page<Post> post = postRepository.findAllByMemberId(memberId, pageable);
//        Page<PostDto> postDto = post.map(PostDto::new);
//        return postDto;
        return postRepository.findAllByMemberId(memberId, pageable).map(PostDto::new);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        List<Post> posts = postRepository.cursorPageFindAllByMemberId(memberId, cursorRequest);
        long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public PageCursor<Post> getPosts(List<Long> memberId, CursorRequest cursorRequest) {
        List<Post> posts = postRepository.cursorPageFindAllByInMemberId(memberId, cursorRequest);
        long nextKey = getNextKey(posts);
        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    public List<Post> getPosts(List<Long> ids) {
        return postRepository.findAllByIdIn(ids);
    }

    private long getNextKey(List<Post> posts) {
        return posts.stream().mapToLong(Post::getId).min().orElse(CursorRequest.NONE_KEY);
    }
}
