package putiez.mbti_putiez.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visitinfo")
public class visitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    // 생성자, 게터, 세터 등

}
