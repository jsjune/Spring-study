package sql.practice.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Team{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEAM_ID")
    private Long Id;
    private String name;

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY,cascade = CascadeType.ALL) //mappdedBy: 연관관계의 주인 필드를 선택한다.
    private List<Member> members = new ArrayList<>();
}
