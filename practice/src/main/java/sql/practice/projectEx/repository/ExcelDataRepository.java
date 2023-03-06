package sql.practice.projectEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sql.practice.projectEx.domain.ExcelData;

public interface ExcelDataRepository extends JpaRepository<ExcelData,Long> {
}
