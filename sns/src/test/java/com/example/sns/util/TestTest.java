package com.example.sns.util;

import com.example.sns.domain.post.dto.DailyPostCount;
import com.example.sns.domain.post.dto.DailyPostCountRequest;
import com.example.sns.domain.post.repository.PostRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void test() {
        DailyPostCountRequest dailyPostCountRequest = new DailyPostCountRequest(1L, LocalDate.of(1970,01,01),
            LocalDate.of(2023,01,01));
        List<DailyPostCount> dailyPostCounts = postRepository.groupByCreateDate(
            dailyPostCountRequest);
        for (DailyPostCount dailyPostCount : dailyPostCounts) {
            System.out.println("dailyPostCount = " + dailyPostCount);
        }
    }
}
