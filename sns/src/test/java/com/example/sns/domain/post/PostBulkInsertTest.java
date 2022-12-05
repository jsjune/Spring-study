package com.example.sns.domain.post;

import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.repository.PostRepository;
import com.example.sns.domain.post.repository.PostRepositoryImpl;
import com.example.sns.util.PostFixtureFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        EasyRandom easyRandom = PostFixtureFactory.get(
            1L,
            LocalDate.of(1978, 1, 1),
            LocalDate.of(2022, 2, 1));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Post> posts = IntStream.range(0, 1_000_000)
            .parallel()
            .mapToObj(i -> easyRandom.nextObject(Post.class))
            .toList();
        stopWatch.stop();
        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());
        StopWatch queryStopWatch = new StopWatch();
        queryStopWatch.start();

        postRepository.bulkInsert(posts);
        queryStopWatch.stop();
        System.out.println("쿼리 시간 : " + queryStopWatch.getTotalTimeSeconds());
    }
}
