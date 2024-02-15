package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import putiez.mbti_putiez.entity.puangMBTI;
import putiez.mbti_putiez.entity.visitCountInfo;

@Repository

public interface mariaJPA_puangMBTI extends JpaRepository<puangMBTI, Long> {
}
