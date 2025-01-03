package org.example.springbatchprac.batch.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p.type FROM Product p")
    List<String> findDistinctTypes();

}
