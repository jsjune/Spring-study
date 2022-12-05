package com.example.sns.application.controller;

import com.example.sns.application.usecase.CreatePostUsecase;
import com.example.sns.application.usecase.GetTimelinePostsUsecase;
import com.example.sns.domain.post.dto.DailyPostCount;
import com.example.sns.domain.post.dto.DailyPostCountRequest;
import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.dto.PostDto;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.service.PostReadService;
import com.example.sns.domain.post.service.PostWriteService;
import com.example.sns.util.CursorRequest;
import com.example.sns.util.PageCursor;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private final GetTimelinePostsUsecase getTimelinePostsUsecase;
    private final CreatePostUsecase createPostUsecase;

    @PostMapping("")
    public Long create(@RequestBody PostCommand command) {
//        return postWriteService.create(command);
        return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(@RequestBody DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

//    @GetMapping("/members/{memberId}")
//    public Page<PostDto> getPosts(
//        @PathVariable Long memberId,
//        @RequestParam Integer page,
//        @RequestParam Integer size) {
//        return postReadService.getPosts(memberId, PageRequest.of(page, size));
//    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(
        @PathVariable Long memberId,
        Pageable pageable) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(
        @PathVariable Long memberId,
        CursorRequest cursorRequest) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
        @PathVariable Long memberId,
        CursorRequest cursorRequest) {
        return getTimelinePostsUsecase.executeTimeLine(memberId, cursorRequest);
//        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
    }


}
