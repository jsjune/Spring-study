package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> forDisplay);

    List<Product> findAllByProductNumberIn(List<String> productNumbers);
}
