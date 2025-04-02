package com.example.springcaffeine.dto;

import com.example.springcaffeine.entity.Feed;

public record FeedInfoDto(Long id, String title, String content) {
    public static FeedInfoDto fromFeed(Feed feed) {
        return new FeedInfoDto(feed.getId(), feed.getTitle(), feed.getContent());
    }
}
