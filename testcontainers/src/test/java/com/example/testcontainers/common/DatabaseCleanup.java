package com.example.testcontainers.common;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.google.common.base.CaseFormat;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseCleanup implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = entityManager.getMetamodel().getEntities().stream() // 엔티티를 돌면서 테이블 이름을 추출한다.
                .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        // 연관 관계 맵핑된 테이블이 있는 경우 참조 무결성을 해제 해주어야 TRUNCATE을 할 수 있다.
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate(); //mysql

        for (String tableName : tableNames) {
            // 테이블 이름을 순회하면서 TRUNCATE SQL문을 수행
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            // 테이블의 내부가 지워지면 그 다음부터는 ID값을 다시 1부터 시작할 수 있도록 기본 값 초기화
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate(); //mysql
        }

        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1;").executeUpdate(); //mysql
    }
}
