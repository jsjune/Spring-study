//package com.example.jwt;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.example.jwt.redis.Point;
//import com.example.jwt.redis.PointRedisRepository;
//import java.time.LocalDateTime;
//import org.aspectj.lang.annotation.After;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//public class RedisTest1 {
//
//    @Autowired
//    private PointRedisRepository pointRedisRepository;
//
////    @AfterEach
////    public void tearDown() throws Exception {
////        pointRedisRepository.deleteAll();
////    }
//
//    @Test
//    public void 기본_등록_조회기능() {
//        //given
//        String id = "jojoldu";
//        LocalDateTime refreshTime = LocalDateTime.of(2018, 5, 26, 0, 0);
//        Point point = Point.builder()
//            .id(id)
//            .amount(1000L)
//            .refreshTime(refreshTime)
//            .build();
//
//        //when
//        pointRedisRepository.save(point);
//
//        //then
//        Point savedPoint = pointRedisRepository.findById(id).get();
//        System.out.println(savedPoint.getId());
//    }
//}
