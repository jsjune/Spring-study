package sql.practice.projectEx.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "team_property")
public class GroupProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;
    private String propertyName;
    private String propertyValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_GROUP_ID")
    private CustomerGroup customerGroup;

    @Builder
    public GroupProperty(String propertyName, CustomerGroup customerGroup) {
        this.propertyName = propertyName;
        this.customerGroup = customerGroup;
    }
}
