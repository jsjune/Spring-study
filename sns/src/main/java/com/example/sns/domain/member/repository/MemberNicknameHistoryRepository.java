package com.example.sns.domain.member.repository;

import com.example.sns.domain.member.entity.Member;
import com.example.sns.domain.member.entity.MemberNicknameHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberNicknameHistoryRepository extends JpaRepository<MemberNicknameHistory, Long> {

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);
}
