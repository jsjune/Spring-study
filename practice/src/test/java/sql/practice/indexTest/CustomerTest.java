package sql.practice.indexTest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static sql.practice.indexTest.QCustomer.customer;

@SpringBootTest
class CustomerTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void insert() {
        List<Customer> customers = new ArrayList<>();

        // 백만 개의 Customer 객체 생성
        for (int i = 0; i < 1000000; i++) {
            String name = "Customer_" + i;
            int age = getRandomAge();
            LocalDateTime createdAt = getRandomCreatedAt();
            Customer customer = new Customer(name, age, createdAt);
            customers.add(customer);
        }
        performBatchInsert(customers);

    }

    @Test
    void test() {
        Instant start = Instant.now();
        List<Customer> fetch = jpaQueryFactory.select(customer)
                .from(customer)
                .orderBy(customer.createdAt.desc())
                .fetch();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("걸린 시간 : " + duration.toMillis() + "ms");

    }

    private void performBatchInsert(List<Customer> customers) {
        String sql = "INSERT INTO customer (name, age, created_at) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Customer customer = customers.get(i);
                ps.setString(1, customer.getName());
                ps.setInt(2, customer.getAge());
                ps.setTimestamp(3, Timestamp.valueOf(customer.getCreatedAt()));
            }

            @Override
            public int getBatchSize() {
                return customers.size();
            }
        });
    }

    private static int getRandomAge() {
        // 랜덤한 나이를 1부터 100 사이에서 생성
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private static LocalDateTime getRandomCreatedAt() {
        // 현재 시간으로부터 최대 5년 전까지의 랜덤한 시간을 생성
        Random random = new Random();
        long randomDays = random.nextInt(365250); // 1825 days = 5 years
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.minus(randomDays, ChronoUnit.DAYS);
    }
}
