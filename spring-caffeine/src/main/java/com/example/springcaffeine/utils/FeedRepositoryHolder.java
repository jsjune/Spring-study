package com.example.springcaffeine.utils;

import com.example.springcaffeine.repository.FeedRepository;
import org.springframework.stereotype.Component;

@Component
public class FeedRepositoryHolder {
    private static FeedRepository feedRepository;

    public FeedRepositoryHolder(FeedRepository feedRepository) {
        FeedRepositoryHolder.feedRepository = feedRepository;
    }

    public static FeedRepository get() {
        return feedRepository;
    }
}
