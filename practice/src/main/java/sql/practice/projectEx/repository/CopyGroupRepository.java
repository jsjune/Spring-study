package sql.practice.projectEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql.practice.projectEx.domain.CopyGroup;

public interface CopyGroupRepository extends JpaRepository<CopyGroup,Long> {
}
