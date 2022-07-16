package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

import java.util.function.LongFunction;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
