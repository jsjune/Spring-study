package sql.practice;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.practice.jpql.Pppp;
import sql.practice.jpql.PpppRespository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class Prac {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Autowired
    PpppRespository ppppRespository;

    @Test
    void ttt() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("팀A");
//            Team team1 = new Team();
//            team1.setName("팀B");
//            Team team2 = new Team();
//            team2.setName("팀C");
//            Member member = new Member();
//            member.setUsername("member1");
//            member.insertTeam(team);
//            Member member1 = new Member();
//            member1.setUsername("member2");
//            member1.insertTeam(team);
//            Member member2 = new Member();
//            member2.setUsername("member3");
//            member2.insertTeam(team1);
//            Member member3 = new Member();
//            member3.setUsername("member4");

//            Team team = new Team();
//            team.setName("팀A");
//            Member member = new Member();
//            member.setUsername("회원1");
//            member.insertTeam(team);
//            Member member1 = new Member();
//            member1.setUsername("회원2");
//            member1.insertTeam(team);
//            Team team1 = new Team();
//            team1.setName("팀B");
//            Team team2 = new Team();
//            team2.setName("팀C");
//            Member member2 = new Member();
//            member2.setUsername("회원3");
//            member2.insertTeam(team1);
//            Member member3 = new Member();
//            member3.setUsername("회원4");
//
//            em.persist(member);
//            em.persist(member1);
//            em.persist(member2);
//            em.persist(member3);
//            em.persist(team2);

            Pppp test = new Pppp();
            ArrayList<String> list = new ArrayList<>();
            list.add("aaaa");
            list.add("bbb");
            list.add("cc");
            list.add("ddd");
            test.setNames(list);
            em.persist(test);

            em.flush();
            em.clear();


            Pppp pppp = em.find(Pppp.class, 1L);
//            Pppp pppp = ppppRespository.findById(1L).get();
            List<String> list1 = pppp.getNames();
            list1.set(1, "qqqq");
            pppp.setNames(list1);
            em.persist(pppp);
            em.flush();
            em.clear();


            System.out.println("============================================");
            System.out.println(pppp.getNames());
//            String jpql = "select distinct t from Team t join fetch t.members where t.name = '팀A'";
////            String jpql = "select t from Team t";
//            List<Team> teams = em.createQuery(jpql, Team.class).getResultList();
//            for (Team team3 : teams) {
//                System.out.println("team3 = " + team3.getMembers());
//            }

//            String jpql = "select m from Member m join fetch m.team";
////            String jpql = "select m from Member m";
//            List<Member> members = em.createQuery(jpql, Member.class).getResultList();
//            for (Member member4 : members) {
//                System.out.println("member4 = " + member4);
//                System.out.println("member4.getTeam().getName() = " + member4.getTeam().getName());
//            }

////
//            String query =
//                    "select new sql.practice.jpql.MemberDTO(m.username, m.age, " +
//                            "case when m.team.Id is null then false " +
//                            "else true end) from Member m";
//            List<MemberDTO> resultList = em.createQuery(query, MemberDTO.class).getResultList();
//            for (MemberDTO memberDTO : resultList) {
//                System.out.println("memberDTO.getA() = " + memberDTO.getA());
//            }

//            String query = "select " +
//                    "case t.name " +
//                    "when '팀A' then '인센티브110%' " +
//                    "when '팀B' then '인센티브120%' " +
//                    "else '인센티브105%' " +
//                    "end " +
//                    "from Team t";
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }

//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println("result.size() = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }
//            List<Member> result = query1.getResultList();
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");
//
//            List<MemberDTO> resultList = em.createQuery("select new sql.practice.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }
}
