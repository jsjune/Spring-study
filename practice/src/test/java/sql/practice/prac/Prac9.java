package sql.practice.prac;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sql.practice.HistoryTest;
import sql.practice.HistoryTestRepository;

import java.time.LocalDateTime;

@SpringBootTest
public class Prac9 {
    @Autowired
    private HistoryTestRepository historyTestRepository;

    @BeforeEach
    public void before() {
        HistoryTest aaa = HistoryTest.builder()
                .name("aaa")
                .createdAt(LocalDateTime.now())
                .build();
        historyTestRepository.save(aaa);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void test() {
        String name = "aaa";
        HistoryTest findHistory = historyTestRepository.findByName(name);
        HistoryTest historyTest = HistoryTest.builder()
                .name(findHistory.getName())
                .updatedAt(findHistory.getUpdatedAt())
                .createdAt(findHistory.getCreatedAt())
                .abTestHistory(findHistory)
                .build();
        historyTestRepository.save(historyTest);
        findHistory.update("bbb");

        String name3 = "bbb";
        HistoryTest findHistory3 = historyTestRepository.findByName(name3);
        HistoryTest historyTest3 = HistoryTest.builder()
                .name(findHistory3.getName())
                .updatedAt(findHistory3.getUpdatedAt())
                .createdAt(findHistory3.getCreatedAt())
                .abTestHistory(findHistory3)
                .build();
        historyTestRepository.save(historyTest3);
        findHistory3.update("ccc");
    }
}
