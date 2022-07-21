package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    
//    @GetMapping("/members")
//    public Page<Member> list(Pageable pageable) {
//        Page<Member> page = memberRepository.findAll(pageable);
//        return page;
//    }

    @GetMapping("/members")
    public Page<MemberDto> list(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        Page<MemberDto> map = page.map(member -> new MemberDto(member.getId(), member.getUsername()));
        Page<MemberDto> pageDto = page.map(MemberDto::new);
        return pageDto;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member("user" + i, i);
            memberRepository.save(member);
        }
    }

}
