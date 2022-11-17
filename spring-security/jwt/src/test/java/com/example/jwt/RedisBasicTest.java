package com.example.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.dialect.JDataStoreDialect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.w3c.dom.CDATASection;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class RedisBasicTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void redisConnectionTest() {
        String key = "a";
        String value = "1";

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);

        String s = valueOperations.get(key);
        assertThat(s).isEqualTo(value);
    }


}
