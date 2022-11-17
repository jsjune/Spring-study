package com.study.springsecurityprac.repository;

import com.study.springsecurityprac.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
