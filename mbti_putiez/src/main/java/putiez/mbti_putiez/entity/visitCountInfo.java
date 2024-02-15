package putiez.mbti_putiez.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "visit_count_info")
@Data
public class visitCountInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String visitName;

    @Column(name = "visit_count")
    private Long visitCount;

    @Column(name ="last_modified")
    private Timestamp lastModified;

}
