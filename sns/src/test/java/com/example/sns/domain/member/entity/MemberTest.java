package com.example.sns.domain.member.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.example.sns.domain.member.repository.MemberRepository;
import com.example.sns.util.MemberFixtureFactory;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    public void testChange() {
        Member member = MemberFixtureFactory.create();
        String expected = "pun";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @Test
    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    public void testNicknameMaxLength() {
        Member member = MemberFixtureFactory.create();
        String over = "punpunpunpunpunpun";

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> member.changeNickname(over)
        );

    }
}