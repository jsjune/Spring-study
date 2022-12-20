package com.example.lock.optimisticLock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<Human, Long> {

    Human findByName(String name);
}
