package com.example.springcaffeine.service;

import com.example.springcaffeine.dto.FeedInfoDto;
import com.example.springcaffeine.dto.FeedRequest;
import com.example.springcaffeine.repository.FeedRepository;
import com.example.springcaffeine.entity.Feed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FeedServiceTest {
    @Autowired
    private FeedService feedService;
    @Autowired
    private FeedRepository feedRepository;

    @BeforeEach
    void setUp() {
        feedRepository.deleteAllInBatch();
    }

    @Test
    void getFeedInfo() {
        // given
        Long id = 1L;

        // when
        feedRepository.save(Feed.of("title", "content"));
        feedService.getFeedInfo(id);

        // then
        feedService.getFeedInfo(id);
    }

    @Test
    void getAllFeeds() {
        // given
        Long id = null;

        // when
        for (int i = 1; i <= 40; i++) {
            feedRepository.save(Feed.of("title" + i, "content" + i));
        }
        feedService.getAllFeeds(id);

        // then
        List<FeedInfoDto> allFeeds = feedService.getAllFeeds(id);
        System.out.println("allFeeds = " + allFeeds);

        FeedRequest req = new FeedRequest("title", "content");
        feedService.saveFeed(req);
        List<FeedInfoDto> allFeeds2 = feedService.getAllFeeds(id);
        System.out.println("allFeeds2 = " + allFeeds2);
    }
}