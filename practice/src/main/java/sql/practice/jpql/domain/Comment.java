package sql.practice.jpql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@AllArgsConstructor
public class Comment extends Heart {
    private String comment;

//    @ManyToOne
//    @JoinColumn(name = "PARENT_ID")
//    private Comment parent;
//
//    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
//    private List<Comment> children = new ArrayList<>();
//
//    @ManyToOne
//    @JoinColumn(name="POST_ID")
//    private Post post;
//
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;


}
