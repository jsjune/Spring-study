package com.example.lock.pessimisticLock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/decrease")
    public String decreaseMoney(@RequestParam String name, @RequestParam int price) {
        String result;
        try {
            homeService.decreasePrice(name, price);
            result = "현재 가격 : " + homeService.currentPrice(name);
        } catch (Exception e) {
            log.info(e.toString());
            result = e.getMessage();
        }
        log.info(result);
        return result;
    }
}
