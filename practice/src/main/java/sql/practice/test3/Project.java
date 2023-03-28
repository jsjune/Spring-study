package sql.practice.test3;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Project(String projectName, User user) {
        this.projectName = projectName;
        this.user = user;
    }
}
