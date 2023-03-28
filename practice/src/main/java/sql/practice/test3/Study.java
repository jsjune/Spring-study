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
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studyName;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Study(String studyName, User user) {
        this.studyName = studyName;
        this.user = user;
    }
}
