package sql.practice.test2SoftDelete;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE groups SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private boolean isDeleted = Boolean.FALSE;

//    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "PROPERTY",
        joinColumns = @JoinColumn(name = "GROUP_ID"))
    private List<Property> properties = new ArrayList<>();

    @Embedded
    private Excel excel = new Excel();

    public Groups(String groupName, List<Property> properties, Excel excel) {
        this.groupName = groupName;
        this.properties = properties;
        this.excel = excel;
    }

    public void update(String groupName,List<Property> properties,Excel excel) {
        this.groupName = groupName;
        this.properties = properties;
        this.excel = excel;
    }

}
