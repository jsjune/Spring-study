package sql.practice.indexTest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "idx_createdAt", columnList = "createdAt"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private LocalDateTime createdAt;

    public Customer(String name, int age, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
    }
}
