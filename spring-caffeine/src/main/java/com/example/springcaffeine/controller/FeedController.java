package com.example.springcaffeine.controller;

import com.example.springcaffeine.dto.FeedInfoDto;
import com.example.springcaffeine.dto.FeedRequest;
import com.example.springcaffeine.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<FeedInfoDto> saveFeed(@RequestBody FeedRequest req) {
        return ResponseEntity.ok().body(feedService.saveFeed(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedInfoDto> getFeedInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(feedService.getFeedInfo(id));
    }

    @GetMapping
    public ResponseEntity<List<FeedInfoDto>> getAllFeeds(@RequestParam(value = "id",required = false) Long id) {
        return ResponseEntity.ok().body(feedService.getAllFeeds(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedInfoDto> updateFeed(@PathVariable("id") Long id, @RequestBody FeedRequest req) {
        return ResponseEntity.ok().body(feedService.updateFeed(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeed(@PathVariable("id") Long id) {
        feedService.deleteFeed(id);
        return ResponseEntity.ok().build();
    }
}
