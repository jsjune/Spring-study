package sql.practice.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
public class Pppp {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "NAMES",
            joinColumns = @JoinColumn(name = "PPPP_ID"))
    private List<String> names = new ArrayList<>();
}

