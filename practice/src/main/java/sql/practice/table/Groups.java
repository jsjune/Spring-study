package sql.practice.table;

import lombok.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE team SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private boolean isDeleted = Boolean.FALSE;

    @ElementCollection
    @CollectionTable(name = "PROPERTIES",
        joinColumns = @JoinColumn(name = "PROPERTY_ID"))
    private List<Property> properties = new ArrayList<>();

    @Embedded
    private Excel excel;

    public Groups(String teamName, List<Property> properties, Excel excel) {
        this.teamName = teamName;
        this.properties = properties;
        this.excel = excel;
    }

}
