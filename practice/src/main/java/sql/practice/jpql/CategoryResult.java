package sql.practice.jpql;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryResult {
    private Long id;
    private String name;
    private Long depth;
    private Long parentid;
    private List<CategoryResult> children;

    public CategoryResult(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.depth = category.getDepth();
        this.parentid = category.getParent()==null ? null : category.getParent().getId();
        this.children = category.getChildren().stream().map(CategoryResult::new).collect(Collectors.toList());
    }

//    public static CategoryResult of(Category category) {
//        return new CategoryResult(
//            category.getId(),
//            category.getName(),
//            category.getDepth(),
//            category.getChildren().stream().map(CategoryResult::of).collect(Collectors.toList())
//        );
//    }
}
