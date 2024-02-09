package putiez.mbti_putiez.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "visitinfo")
@Data
public class visitInfo {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department")
    private String department;

    @Column(name = "mbti")
    private String mbti;

    @Column(name ="createtime")
    private Timestamp createtime;
    // 생성자, 게터, 세터 등

}
