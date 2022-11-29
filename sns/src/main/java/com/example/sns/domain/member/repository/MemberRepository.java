package com.example.sns.domain.member.repository;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findAllByIdIn(List<Long> ids);
}
