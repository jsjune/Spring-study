package com.example.demo.service;

import com.example.demo.domain.Coupon;
import com.example.demo.producer.CouponCreateProducer;
import com.example.demo.repository.AppliedUserRepository;
import com.example.demo.repository.CouponCountRepository;
import com.example.demo.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public void apply(Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if (apply != 1) {
            return;
        }

//        long count = couponRepository.count();
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

//        couponRepository.save(new Coupon(userId));
        couponCreateProducer.create(userId);

    }
}
