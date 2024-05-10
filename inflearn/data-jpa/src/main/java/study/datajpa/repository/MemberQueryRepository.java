package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

// 중요 핵심 로직은 따로 분리하는거 권장

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {
    private final EntityManager em;
}
