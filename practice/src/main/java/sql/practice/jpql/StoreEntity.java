package sql.practice.jpql;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
public class StoreEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String storeName;
    private String address;
}
