//package sql.practice.projectEx;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.Data;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.StopWatch;
//import sql.practice.projectEx.domain.CustomerGroup;
//import sql.practice.projectEx.domain.ExcelData;
//import sql.practice.projectEx.domain.ExcelFile;
//import sql.practice.projectEx.domain.GroupProperty;
//import sql.practice.projectEx.repository.CustomerGroupRepository;
//import sql.practice.projectEx.repository.ExcelDataRepository;
//import sql.practice.projectEx.repository.ExcelFileRepository;
//import sql.practice.projectEx.repository.GroupPropertyRepository;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static sql.practice.projectEx.domain.QCustomerGroup.customerGroup;
//import static sql.practice.projectEx.domain.QGroupProperty.groupProperty;
//
//@SpringBootTest
//public class CustomerGroupDetailTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private CustomerGroupRepository customerGroupRepository;
//    @Autowired
//    private ExcelDataRepository excelDataRepository;
//    @Autowired
//    private ExcelFileRepository excelFileRepository;
//    @Autowired
//    private GroupPropertyRepository groupPropertyRepository;
//
//    @BeforeEach
//    void before() {
//        CustomerGroup group = CustomerGroup.builder()
//                .name("group")
//                .createdAt(LocalDateTime.now())
//                .build();
//        customerGroupRepository.save(group);
//        GroupProperty property1 = GroupProperty.builder()
//                .propertyName("property1")
//                .customerGroup(group)
//                .build();
//        GroupProperty property2 = GroupProperty.builder()
//                .propertyName("property2")
//                .customerGroup(group)
//                .build();
////        group.addProperty(property1);
////        group.addProperty(property2);
//        groupPropertyRepository.save(property1);
//        groupPropertyRepository.save(property2);
//        ExcelFile excelFile = ExcelFile.builder()
//                .excelFileOrgName("aaa.csv")
//                .customerGroup(group)
//                .build();
//        excelFileRepository.save(excelFile);
//        ExcelData aaa = ExcelData.builder()
//                .username("aaa")
//                .excelFile(excelFile)
//                .build();
//        ExcelData bbb = ExcelData.builder()
//                .username("bbb")
//                .excelFile(excelFile)
//                .build();
//        ExcelData ccc = ExcelData.builder()
//                .username("ccc")
//                .excelFile(excelFile)
//                .build();
//        excelDataRepository.save(aaa);
//        excelDataRepository.save(bbb);
//        excelDataRepository.save(ccc);
//    }
//
//    @Test
//    void detail() {
////        StopWatch stopWatch1 = new StopWatch();
////        stopWatch1.start();
////        List<ResponseDto> result = jpaQueryFactory.select(Projections.constructor(ResponseDto.class,
////                        customerGroup))
////                .from(customerGroup)
////                .leftJoin(groupProperty).on(groupProperty.customerGroup.eq(customerGroup)).fetchJoin()
////                .distinct()
////                .fetch();
////        stopWatch1.stop();
////        System.out.println("시간 : " + stopWatch1.getTotalTimeMillis());
////        System.out.println(result);
//
//        CustomerGroup fetch = jpaQueryFactory.select(
//                        customerGroup)
//                .from(customerGroup)
//                .leftJoin(customerGroup.propertyList, groupProperty).fetchJoin()
//                .where(customerGroup.customerGroupId.eq(1L))
//                .distinct()
//                .fetchOne();
//
//        ResponseDto response = new ResponseDto(fetch);
//        System.out.println(response);
//    }
//
//    @Data
//    public static class ResponseDto{
//        private String groupName;
//        private List<PropertyDto> groupProperties;
//
//        public ResponseDto(CustomerGroup customerGroup) {
//            this.groupName = customerGroup.getName();
//            this.groupProperties = customerGroup.getPropertyList().stream()
//                    .map(PropertyDto::new)
//                    .collect(Collectors.toList());
//        }
//    }
//
//    @Data
//    public static class PropertyDto{
//        private String propertyName;
//
//        public PropertyDto(GroupProperty groupProperty) {
//            this.propertyName = groupProperty.getPropertyName();
//        }
//    }
//}
