package com.example.lock.optimisticLock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HumanService {

    private final HumanRepository humanRepository;

    public int currentMoney(String name) {
        Human human = humanRepository.findByName(name);
        return human.getMoney();
    }

    @Transactional
    public void decreaseMoney(String name, int money) throws IllegalAccessException {
        Human human = humanRepository.findByName(name);
        human.decreaseMoney(money);
    }
}
