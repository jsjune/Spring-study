package org.example.chatservice.repository;

import java.util.Optional;
import org.example.chatservice.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByName(String username);
}
