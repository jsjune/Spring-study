//package sql.practice;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import sql.practice.jpql.domain.Heart;
//import sql.practice.jpql.domain.Post;
//import sql.practice.jpql.domain.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.PersistenceUnit;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@SpringBootTest
//public class Prac3 {
//    @PersistenceUnit
//    EntityManagerFactory emf;
//    @Test
//    @Transactional
//    void ttt() {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//
//            User user = new User();
//            Post post = new Post();
//
//            em.persist(post);
//            em.persist(user);
//            em.flush();
//            em.clear();
//
//            Post post1 = em.find(Post.class, 1L);
//            User user1 = em.find(User.class, 1L);
//            Heart heart = post1.getHeart();
//            heart.setUser(user1);
//            post1.setHeart(heart);
//            List<Heart> hearts = post1.getHearts();
//            hearts.add(heart);
//            post1.setHearts(hearts);
//
//            em.persist(post1);
//            em.persist(user1);
//            em.flush();
//            em.clear();
//
//            Post findPost = em.find(Post.class, 1L);
//            System.out.println(findPost.getHearts().get(0));
//
//
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        emf.close();
//
//    }
//}
