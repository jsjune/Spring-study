package sql.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.practice.jpql.Member;
import sql.practice.jpql.MemberRepository;

@SpringBootTest
public class Prac7 {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void before() {
        Member member = Member.builder()
            .age(10)
            .username("aaa")
            .build();
        memberRepository.save(member);

    }

    @Test
    void test() {
        memberRepository.findById(1l);
        System.out.println("==============================");
        memberRepository.deleteById(1l);
    }
}
