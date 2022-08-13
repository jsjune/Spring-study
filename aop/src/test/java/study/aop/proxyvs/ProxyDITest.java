package study.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.aop.member.MemberService;
import study.aop.member.MemberServiceImpl;
import study.aop.proxyvs.code.ProxyDIAspect;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시, DI 예외 발생
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 프록시, 성공
@SpringBootTest
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
