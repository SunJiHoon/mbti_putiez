package putiez.mbti_putiez.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "share_info")
@Data
public class ShareInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "createTime")
    private Timestamp createTime;

    @Column(name = "status")
    private String status;

}
