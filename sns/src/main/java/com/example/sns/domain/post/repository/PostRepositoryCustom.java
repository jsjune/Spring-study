package com.example.sns.domain.post.repository;

import com.example.sns.domain.post.dto.DailyPostCount;
import com.example.sns.domain.post.dto.DailyPostCountRequest;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.util.CursorRequest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {

    List<DailyPostCount> groupByCreateDate(DailyPostCountRequest request);

    void bulkInsert(List<Post> posts);

    List<Post> cursorPageFindAllByMemberId(Long memberId, CursorRequest cursorRequest);

    List<Post> cursorPageFindAllByInMemberId(List<Long> memberId, CursorRequest cursorRequest);
}
