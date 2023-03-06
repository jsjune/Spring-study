package sql.practice.jpql;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
public class FoodEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String foodName;
    private String foodCalorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public StoreEntity storeEntity;
}
