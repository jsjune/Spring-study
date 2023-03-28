package sql.practice.test2;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sql.practice.test2.QGroups.groups;

@SpringBootTest
public class TestDeocode {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void before() {
        for (int k = 0; k < 10; k++) {
            List<Property> properties = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Property property = new Property("aaa" + i);
                properties.add(property);
            }
            List<Data> datas = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Data data = new Data("username" + i, "number" + i);
                datas.add(data);
            }
            Excel excel = new Excel("엑셀", "경로", datas);
            Groups group = new Groups("A팀", properties, excel);
            groupRepository.save(group);
        }
    }
    
    @Test
    @Transactional
    @Rollback(value = false)
    void findAll() {
        List<Groups> all = groupRepository.findAll();
        System.out.println("==========================================================");
        for (Groups groups1 : all) {
            List<Data> dataList = groups1.getExcel().getDataList();
            for (Data data : dataList) {
                System.out.println("data.getName() = " + data.getName());
            }
            List<Property> properties = groups1.getProperties();
            for (Property property : properties) {
                System.out.println("property.getName() = " + property.getName());
            }
        }
        System.out.println("==========================================================");
    }

    @Test
    void delete() {
        groupRepository.deleteById(1L);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void update() {
        Groups group = groupRepository.findById(1L).get();
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Data data = new Data("updateName" + i, "updateNumber" + i);
            datas.add(data);
        }
        Excel excel = new Excel("엑셀", "경로", datas);
        List<Property> properties = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Property property = new Property("bbb" + i);
            properties.add(property);
        }
        group.update("B팀",properties,excel);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void get() {
        Groups group = jpaQueryFactory.select(groups)
                .from(groups)
                .leftJoin(groups.properties)
                .leftJoin(groups.excel.dataList)
                .where(groups.id.eq(1L))
                .fetchOne();
        Response response = new Response(group);
        System.out.println("response = " + response);
    }

    @lombok.Data
    public static class Response {
        private String teamName;
        private String orgName;
        private String saveName;
        private List<String> properties;
        private List<DataDto> datas;

        public Response(Groups groups) {
            this.teamName = groups.getGroupName();
            this.orgName = groups.getExcel().getFileOrgName();
            this.saveName = groups.getExcel().getFileSaveName();
            this.properties = groups.getProperties().stream().map(Property::getName).collect(Collectors.toList());
            this.datas = groups.getExcel().getDataList().stream().map(DataDto::new).collect(Collectors.toList());
        }
    }

    @lombok.Data
    public static class PropertyDto {
        private String name;

        public PropertyDto(Property property) {
            this.name = property.getName();
        }
    }

    @lombok.Data
    public static class DataDto {
        private String name;
        private String phoneNumber;

        public DataDto(Data data) {
            this.name = data.getName();
            this.phoneNumber = data.getPhoneNumber();
        }
    }


    @lombok.Data
    public static class Aaa {
        private Groups groups;

        public Aaa(Groups groups) {
            this.groups = groups;
        }
    }
}
