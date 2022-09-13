package sql.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sql.practice.jpql.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class Prac5 {
    @PersistenceUnit
    EntityManagerFactory emf;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

//    @BeforeEach
    public void init() {
        Team team = new Team();
        team.setName("팀1");
        Member member = new Member();
        member.setUsername("맴버1");
//        member.insertTeam(team);
        teamRepository.save(team);
        memberRepository.save(member);
    }

    @Test
    void ttt() {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        emf.close();



        Team team = Team.builder()
                .name("가나다")
                .build();

        Member member = Member.builder()
                .age(19)
                .username("이름")
                .team(team)
                .build();
        team.add(member);

        teamRepository.save(team);
        memberRepository.save(member);
        System.out.println(team);
    }
}
