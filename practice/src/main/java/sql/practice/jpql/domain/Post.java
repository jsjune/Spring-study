package sql.practice.jpql.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter
@Setter
public class Post extends Heart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String name;

//    @Embedded
//    private Heart heart;
//
//    @CollectionTable(name = "hearts",
//            joinColumns = @JoinColumn(name = "post_id"))
//    @ElementCollection
//    private List<Heart> hearts = new ArrayList<Heart>();

}
