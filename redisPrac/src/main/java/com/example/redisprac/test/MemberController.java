package com.example.redisprac.test;

import java.util.List;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/member/{id}")
    @Cacheable(value = "Member", key = "#id")
    public Member getMember(@PathVariable Long id) {
        return memberRepository.findById(id).get();
    }

    @PostMapping("/member")
    public void saveMember(@RequestBody RequestDto requestDto) {
        Member member = Member.builder()
            .username(requestDto.username)
            .age(requestDto.age)
            .build();
        memberRepository.save(member);
    }

    @PutMapping("/member/{id}")
    @CacheEvict(value = "Member", key = "#id")
    @Transactional
    public void updateMember(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        Member findMember = memberRepository.findById(id).get();
        findMember.setUsername(requestDto.username);
        findMember.setAge(requestDto.age);
    }

    @GetMapping("/member")
    @Cacheable("AllMember")
    public List<Member> getMembers() {
        return (List<Member>)memberRepository.findAll();
    }

    @Getter
    private static class RequestDto {
        private String username;
        private int age;
    }
}
