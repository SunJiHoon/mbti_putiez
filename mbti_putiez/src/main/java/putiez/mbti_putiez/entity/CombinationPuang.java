package putiez.mbti_putiez.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "combination_puang")
@Data
public class CombinationPuang {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_puang_id")
    private puangMBTI puangMBTI;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "combination_mbti")
    private String CombinationPuangMBTI;

    @Column(name = "status")
    private String status;
}
