package sql.practice.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter @Setter
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stockAmount;
}
