package sql.practice.table;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static sql.practice.table.QGroups.groups;
import static sql.practice.table.QProperty.property;

@SpringBootTest
public class TestDeocode {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void before() {
        List<Property> properties = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Property member = new Property("aaa" + i);
            properties.add(member);
        }
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Data data = new Data("username" + i, "number" + i);
            datas.add(data);
        }
        Excel excel = new Excel("엑셀", "경로", datas);
        Groups group = new Groups("A팀", properties, excel);
        groupRepository.save(group);
    }

    @Test
//    @Transactional
//    @Rollback(value = false)
    void test() {
        List<Groups> fetch = jpaQueryFactory.select(
                        groups
                )
                .from(groups)
                .join(groups.properties, property)
                .fetch();
        System.out.println("fetch = " + fetch);
//        List<String> fetch1 = jpaQueryFactory.select(property.name)
//                .from(groups)
//                .join(groups.properties, property)
//                .fetch();
//        System.out.println("fetch1 = " + fetch1);
    }

    @lombok.Data
    public static class Response {
        private String teamName;
        private String orgName;
        private String saveName;
        private List<String> name;

        public Response(String teamName, String orgName, String saveName, String name) {
            this.teamName = teamName;
            this.orgName = orgName;
            this.saveName = saveName;
            this.name.add(name);
        }
    }


}
