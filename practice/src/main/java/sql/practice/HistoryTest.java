package sql.practice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "HISTORY_TEST_ID")
    private HistoryTest abTestHistory;

    public void update(String name) {
        this.updatedAt = LocalDateTime.now();
        this.name = name;
    }
}
