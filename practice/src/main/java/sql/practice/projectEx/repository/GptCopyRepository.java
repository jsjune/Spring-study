package sql.practice.projectEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql.practice.projectEx.domain.GptCopy;

public interface GptCopyRepository extends JpaRepository<GptCopy,Long> {
}
