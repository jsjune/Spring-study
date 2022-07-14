package sql.practice.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)// ManyToOne만 쓰면 N:1 단방향
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void insertTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

