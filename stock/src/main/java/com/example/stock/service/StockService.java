package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, Long quantity) {
        // Stock 조회
        // 재고를 감소한뒤
        // 갱신된 값을 저장하도록 한다.

        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);

    }

//    public synchronized void decrease(Long id, Long quantity) { // 멀티프로세스 환경에서 문제가 일어남
//        // Stock 조회
//        // 재고를 감소한뒤
//        // 갱신된 값을 저장하도록 한다.
//
//        Stock stock = stockRepository.findById(id).orElseThrow();
//        stock.decrease(quantity);
//        stockRepository.saveAndFlush(stock);
//
//    }
}
