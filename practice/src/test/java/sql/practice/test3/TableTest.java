package sql.practice.test3;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static sql.practice.test3.QProject.project;
import static sql.practice.test3.QStudy.study;
import static sql.practice.test3.QUser.user;

@SpringBootTest
public class TableTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void before() {
        for (int i = 0; i < 10; i++) {
            User user = new User("user" + i);
            userRepository.save(user);
            List<Study> studyList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Study study = new Study("study" + j, user);
                studyList.add(study);
            }
            studyRepository.saveAll(studyList);
            List<Project> projectList = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                Project project = new Project("project" + j, user);
                projectList.add(project);
            }
            projectRepository.saveAll(projectList);
        }

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void test() {
//        List<User> users = jpaQueryFactory.selectFrom(user).fetch();
//        users.stream().map(User::getStudies).forEach(Hibernate::initialize);
//        users.stream().map(User::getProjects).forEach(Hibernate::initialize);
        List<User> users = userRepository.findAll();

        System.out.println("================================");
        for (User user1 : users) {
            List<Study> studies = user1.getStudies();
            for (Study study1 : studies) {
                System.out.println("study1.getStudyName() = " + study1.getStudyName());
            }
            List<Project> projects = user1.getProjects();
            for (Project project1 : projects) {
                System.out.println("project1 = " + project1.getProjectName());
            }
        }
        System.out.println("================================");
    }
}
