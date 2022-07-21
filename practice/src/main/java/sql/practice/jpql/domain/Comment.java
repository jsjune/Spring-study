package sql.practice.jpql.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Comment> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
