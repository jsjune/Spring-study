package study.aop.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.aop.exam.annotation.Trace;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
