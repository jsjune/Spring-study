package sql.practice.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
public class Property {

    private String name;

    public Property(String name) {
        this.name = name;
    }
}
