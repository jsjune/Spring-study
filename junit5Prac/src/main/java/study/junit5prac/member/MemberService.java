package study.junit5prac.member;

import study.junit5prac.domain.Member;
import study.junit5prac.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}
