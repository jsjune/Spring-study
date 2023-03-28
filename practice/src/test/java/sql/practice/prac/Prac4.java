package sql.practice.prac;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sql.practice.jpql.Follow;
import sql.practice.jpql.domain.Heart;
import sql.practice.jpql.domain.Post;
import sql.practice.jpql.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@SpringBootTest
public class Prac4 {
    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    @Transactional
    void ttt() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            User user = User.builder()
                    .name("정상준")
                    .build();


            User follower = new User(); // 장민우
//            User follower2 = new User(); // 오예령

            Follow follow = new Follow();
            follow.setFollow(user);
            follow.setFollower(follower);

            em.persist(user);
            em.persist(follower);
            em.persist(follow);
            em.flush();
            em.clear();


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
