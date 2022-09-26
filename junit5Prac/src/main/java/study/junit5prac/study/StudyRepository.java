package study.junit5prac.study;

import org.springframework.data.jpa.repository.JpaRepository;
import study.junit5prac.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
