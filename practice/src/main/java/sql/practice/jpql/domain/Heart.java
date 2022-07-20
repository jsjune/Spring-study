package sql.practice.jpql.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Getter
@Setter
public class Heart {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
