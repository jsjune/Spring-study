package com.example.testcontainers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<Human,Long> {
    Human findByUsername(String username);
}
