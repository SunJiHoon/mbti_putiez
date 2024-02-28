package putiez.mbti_putiez.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "percent")
@Data
public class Percent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "percentage")
    private Double percentage;

    // 필드와 게터/세터 메서드 생략

}
