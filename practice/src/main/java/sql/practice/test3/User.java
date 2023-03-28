package sql.practice.test3;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

//    @BatchSize(size = 100)
    @OneToMany(mappedBy = "user")
    private List<Project> projects = new ArrayList<>();

//    @BatchSize(size = 100)
    @OneToMany(mappedBy = "user")
    private List<Study> studies = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }
}
