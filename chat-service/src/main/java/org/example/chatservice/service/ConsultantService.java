package org.example.chatservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dtos.ChatroomDto;
import org.example.chatservice.dtos.MemberDto;
import org.example.chatservice.entities.Member;
import org.example.chatservice.entities.Role;
import org.example.chatservice.repository.ChatroomRepository;
import org.example.chatservice.repository.MemberRepository;
import org.example.chatservice.vos.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultantService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ChatroomRepository chatroomRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        if (Role.fromCode(member.getRole()) != Role.CONSULTANT) {
            throw new AccessDeniedException("상담사가 아닙니다.");
        }
        return new CustomUserDetails(member, null);
    }

    public MemberDto saveMember(MemberDto memberDto) {
        Member member = MemberDto.to(memberDto);
        member.updatePassword(memberDto.password(), memberDto.confirmedPassword(), passwordEncoder);
        return MemberDto.from(memberRepository.save(member));
    }

    public Page<ChatroomDto> getAllChatrooms(Pageable pageable) {
        return chatroomRepository.findAll(pageable)
            .map(ChatroomDto::from);
    }
}
