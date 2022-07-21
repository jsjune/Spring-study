package sql.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.practice.jpql.domain.Comment;
import sql.practice.jpql.CommentRepository;
import sql.practice.jpql.domain.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@SpringBootTest
public class Prac2 {
    @PersistenceUnit
    EntityManagerFactory emf;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach // 테스트 시작전 데이터 주입입
    public void before() {

    }

    @Test
    @Transactional
    void ttt() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Post post = new Post();

            Comment comment = new Comment();
            comment.setComment("가나다");
            comment.setPost(post);

            Comment comment1 = new Comment();
            comment1.setComment("라마바");
            comment1.setParent(comment);

            Comment comment2 = new Comment();
            comment2.setComment("사아자");
            comment2.setParent(comment1);

            Comment comment3 = new Comment();
            comment3.setComment("차카타");
            comment3.setParent(comment);

            em.persist(comment);
            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);

            em.flush();
            em.clear();

            System.out.println("==================================================");
//            em.createQuery("select ")
            System.out.println("==================================================");


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
