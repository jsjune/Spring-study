package sql.practice.projectEx.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExcelData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long excelDataId;
    private String username;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "EXCEL_FILE_ID")
    private ExcelFile excelFile;

    @Builder
    public ExcelData(String username, ExcelFile excelFile) {
        this.username = username;
        this.excelFile = excelFile;
    }
}
