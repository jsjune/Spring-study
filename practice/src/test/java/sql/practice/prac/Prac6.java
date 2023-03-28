//package sql.practice.prac;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import sql.practice.jpql.Category;
//import sql.practice.jpql.CategoryRepository;
//import sql.practice.jpql.CategoryResult;
//import sql.practice.jpql.CategoryService;
//
//@SpringBootTest
//public class Prac6 {
//
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @BeforeEach
//    public void init() {
//        Category category1 = Category.builder()
//            .name("출산유아동")
//            .depth(1l)
//            .build();
//        Category category2 = Category.builder()
//            .name("유아동잡화")
//            .depth(2l)
//            .parent(category1)
//            .build();
//        Category category3 = Category.builder()
//            .name("장화화우비우산")
//            .depth(3l)
//            .parent(category2)
//            .build();
//        ArrayList<Category> arr = new ArrayList<>();
//        arr.add(category2);
//        ArrayList<Category> arr2 = new ArrayList<>();
//        arr2.add(category3);
//
//        category1.setChildren(arr);
//        category2.setChildren(arr2);
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//    }
//
//    @Test
//    void 테스트() {
//        List<CategoryResult> categoryResult = categoryService.getCategoryResult();
//        for (CategoryResult result : categoryResult) {
//            System.out.println("result = " + result);
//        }
//    }
//}
