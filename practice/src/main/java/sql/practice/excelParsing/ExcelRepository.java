package sql.practice.excelParsing;

import org.springframework.data.jpa.repository.JpaRepository;
import sql.practice.jpql.domain.Comment;

public interface ExcelRepository extends JpaRepository<ExcelEntity,Long> {

}
