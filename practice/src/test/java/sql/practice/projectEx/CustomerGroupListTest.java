//package sql.practice.projectEx;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.Data;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import sql.practice.projectEx.domain.*;
//import sql.practice.projectEx.repository.*;
//
//import java.time.LocalDateTime;
//
//import static sql.practice.projectEx.domain.QCustomerGroup.customerGroup;
//import static sql.practice.projectEx.domain.QExcelData.excelData;
//import static sql.practice.projectEx.domain.QExcelFile.excelFile;
//
//
//@SpringBootTest
//public class CustomerGroupListTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private CustomerGroupRepository customerGroupRepository;
//    @Autowired
//    private ExcelDataRepository excelDataRepository;
//    @Autowired
//    private ExcelFileRepository excelFileRepository;
//
//    @BeforeEach
//    void before() {
//        CustomerGroup group = CustomerGroup.builder()
//                .name("group")
//                .favorite(true)
//                .createdAt(LocalDateTime.now())
//                .build();
//        customerGroupRepository.save(group);
//        ExcelFile excelFile = ExcelFile.builder()
//                .excelFileOrgName("aaa.excel")
//                .customerGroup(group)
//                .build();
//        excelFileRepository.save(excelFile);
//        ExcelData excelData1 = ExcelData.builder()
//                .username("aaa")
//                .excelFile(excelFile)
//                .build();
//        ExcelData excelData2 = ExcelData.builder()
//                .username("bbb")
//                .excelFile(excelFile)
//                .build();
//        ExcelData excelData3 = ExcelData.builder()
//                .username("ccc")
//                .excelFile(excelFile)
//                .build();
//        excelDataRepository.save(excelData1);
//        excelDataRepository.save(excelData2);
//        excelDataRepository.save(excelData3);
//    }
//
//    @Test
//    void sqlTest() {
//        ResponseDto responseDto = jpaQueryFactory.select(Projections.constructor(ResponseDto.class,
//                        customerGroup,
//                        excelData.excelFile.count()
//                ))
//                .from(customerGroup)
//                .join(excelFile).on(excelFile.customerGroup.eq(customerGroup))
//                .join(excelData).on(excelData.excelFile.eq(excelFile))
//                .groupBy(excelData.excelFile)
//                .fetchOne();
//        System.out.println(responseDto);
//    }
//
//    @Data
//    public static class ResponseDto{
//        private Long customerGroupId;
//        private String groupName;
//        private Long customerCnt;
//        private Boolean favorite;
//        private LocalDateTime createAt;
//
//        public ResponseDto(CustomerGroup customerGroup,Long customerCnt) {
//            this.customerGroupId = customerGroup.getCustomerGroupId();
//            this.groupName = customerGroup.getName();
//            this.favorite = customerGroup.getFavorite();
//            this.createAt = customerGroup.getCreatedAt();
//            this.customerCnt = customerCnt;
//        }
//
//    }
//}
