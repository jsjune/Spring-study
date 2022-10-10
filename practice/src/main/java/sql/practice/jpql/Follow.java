package sql.practice.jpql;

import lombok.Getter;
import lombok.Setter;
import sql.practice.jpql.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Follow {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private User follow;

    @ManyToOne
    private User follower;

}
