package sql.practice.projectEx.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "customer_team")
public class CustomerGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerGroupId;
    private String name;
    private Boolean favorite;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "customerGroup")
    private List<GroupProperty> propertyList = new ArrayList<>();

    @Builder
    public CustomerGroup(String name, Boolean favorite, LocalDateTime createdAt, List<GroupProperty> propertyList) {
        this.name = name;
        this.favorite = favorite;
        this.createdAt = createdAt;
        this.propertyList = propertyList;
    }

    public void addProperty(GroupProperty property) {
        property.setCustomerGroup(this);
        propertyList.add(property);
    }
}
