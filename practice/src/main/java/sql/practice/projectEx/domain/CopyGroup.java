package sql.practice.projectEx.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "copy_team")
public class CopyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long copyGroupId;
    private String copyGroupName;
    private String tag;
    private String brandName;
    private String productName;
    private String keyword;
    private String type;
    private Boolean favorite;
    private Integer createCount;
    private Integer copyLength;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
