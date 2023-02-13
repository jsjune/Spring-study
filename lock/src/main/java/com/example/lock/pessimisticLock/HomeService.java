package com.example.lock.pessimisticLock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {

    private final HomeRepository homeRepository;

    @Transactional
    public int currentPrice(String name) {
        Home home = homeRepository.findByName(name);
        return home.getPrice();
    }

    @Transactional
    public void decreasePrice(String name, int price) throws IllegalAccessException {
        Home home = homeRepository.findByName(name);
//        Home home = homeRepository.findWithNameForUpdate(name);
        home.decreasePrice(price);
    }
}
