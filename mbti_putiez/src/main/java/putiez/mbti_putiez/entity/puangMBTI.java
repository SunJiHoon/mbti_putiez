package putiez.mbti_putiez.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "puang_mbti")
@Data
public class puangMBTI {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "explanation")
    private String explanation;

    @OneToMany(mappedBy = "puangMBTI", cascade = CascadeType.ALL)
    private List<CombinationPuang> combinationPuangList = new ArrayList<>();
}
