package com.example.lock.optimisticLock;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Human {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Integer money;
    private LocalDate birth;

    @Version
    private Integer version;

    public Human(String name, Integer money, LocalDate birth) {
        this.name = name;
        this.money = money;
        this.birth = birth;
    }

    public int decreaseMoney(int money) throws IllegalAccessException {
        if (this.money - money < 0) {
            throw new IllegalAccessException("돈이 부족해");
        }
        return this.money -= money;
    }
}
