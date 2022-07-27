package sql.practice.jpql.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    private Long id;

    private String name;

    public User() {

    }

    @Builder public User(String name) {
        this.name = name;
    }
}
