package sql.practice.jpql;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Setter @Getter
@NoArgsConstructor
@ToString
public class Team{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEAM_ID")
    private Long Id;
    private String name;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL) //mappdedBy: 연관관계의 주인 필드를 선택한다.
    private List<Member> members = new ArrayList<>();

    @Builder
    public Team(String name) {
        this.name = name;
    }

    public void add(Member member) {
        member.setTeam(this);
        getMembers().add(member);
    }

}
