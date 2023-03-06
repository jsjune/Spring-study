package sql.practice.projectEx.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExcelFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long excelFileId;
    private String excelFileOrgName;
    private String excelFileSavedName;
    private String excelFileSavedPath;
    private String excelFileSize;
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_GROUP_ID")
    private CustomerGroup customerGroup;

    @Builder
    public ExcelFile(String excelFileOrgName, CustomerGroup customerGroup) {
        this.excelFileOrgName = excelFileOrgName;
        this.customerGroup = customerGroup;
    }
}
