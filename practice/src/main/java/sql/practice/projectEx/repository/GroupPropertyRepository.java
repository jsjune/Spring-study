package sql.practice.projectEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql.practice.projectEx.domain.GroupProperty;

public interface GroupPropertyRepository extends JpaRepository<GroupProperty,Long> {
}
