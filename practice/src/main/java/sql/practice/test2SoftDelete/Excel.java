package sql.practice.test2SoftDelete;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Excel {
    private String fileOrgName;
    private String fileSaveName;

//    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "DATA",
            joinColumns = @JoinColumn(name = "EXCEL_ID"))
    private List<Data> dataList = new ArrayList<>();


    public Excel(String fileOrgName, String fileSaveName, List<Data> dataList) {
        this.fileOrgName = fileOrgName;
        this.fileSaveName = fileSaveName;
        this.dataList = dataList;
    }
}
