package sql.practice.table;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@NoArgsConstructor
public class Excel {
    private String orgName;
    private String saveName;

    @ElementCollection
    @CollectionTable(name = "DATAS",
            joinColumns = @JoinColumn(name = "EXCEL_ID"))
    private List<Data> dataList = new ArrayList<>();

    public Excel(String orgName, String saveName, List<Data> dataList) {
        this.orgName = orgName;
        this.saveName = saveName;
        this.dataList = dataList;
    }
}
