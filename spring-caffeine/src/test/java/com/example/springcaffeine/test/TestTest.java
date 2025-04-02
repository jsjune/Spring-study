package com.example.springcaffeine.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class TestTest {
    public static Map<String, Integer> hMap = new HashMap<>();
    public static Map<String, Integer> cMap = new ConcurrentHashMap<>();
    public static Hashtable<String, Integer> hTable = new Hashtable<>();

    @Test
    void test01() throws InterruptedException {
        int cnt = 1000; // 동시 요청
        hMap.put("a", cnt);


        ExecutorService ex = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(cnt);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < cnt; i++) {
            ex.execute(() -> {
                try {
//                    hMap.put("a", hMap.get("a") - 1);
                    hMap.compute("a", (k, v) -> v - 1);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        assertThat(hMap.get("a")).isNotEqualTo(0);
        assertThat(successCount.get()).isEqualTo(cnt);
        assertThat(failCount.get()).isEqualTo(0);
    }

    @Test
    void test02() throws InterruptedException {
        int cnt = 1000; // 동시 요청
        cMap.put("a", cnt);

        ExecutorService ex = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(cnt);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < cnt; i++) {
            ex.execute(() -> {
                try {
                    cMap.compute("a", (k, v) -> v - 1);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        assertThat(cMap.get("a")).isEqualTo(0);
        assertThat(successCount.get()).isEqualTo(cnt);
        assertThat(failCount.get()).isEqualTo(0);
    }

    @Test
    void test03() throws InterruptedException {
        int cnt = 1000; // 동시 요청
        hTable.put("a", cnt);

        ExecutorService ex = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(cnt);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < cnt; i++) {
            ex.execute(() -> {
                try {
                    hTable.compute("a", (k, v) -> v - 1);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        assertThat(hTable.get("a")).isEqualTo(0);
        assertThat(successCount.get()).isEqualTo(cnt);
        assertThat(failCount.get()).isEqualTo(0);
    }
}