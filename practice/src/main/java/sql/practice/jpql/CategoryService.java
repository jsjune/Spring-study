package sql.practice.jpql;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResult> getCategoryResult() {
        List<CategoryResult> collect = categoryRepository.findAll().stream()
            .map(CategoryResult::new).collect(Collectors.toList());
        return collect;
    }
}
