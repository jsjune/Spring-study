package com.example.testcontainers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HumanService {
    private final HumanRepository humanRepository;

    public Human save() {
        return humanRepository.save(new Human("aaa", 20));
    }
}
