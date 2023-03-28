package sql.practice.projectEx.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GptCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gptCopyId;
    private String content;
    private String state;

    @ManyToOne
    @JoinColumn(name = "COPY_GROUP_ID")
    private CopyGroup copyGroup;
}
