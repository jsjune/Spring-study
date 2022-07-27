package sql.practice.jpql.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
//@DiscriminatorColumn
public abstract class Heart {
    @Id @GeneratedValue
    private Long id;

}
