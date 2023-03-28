package sql.practice.test2;

import lombok.*;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Property {

    private String name;
    public Property(String name) {
        this.name = name;
    }
}
