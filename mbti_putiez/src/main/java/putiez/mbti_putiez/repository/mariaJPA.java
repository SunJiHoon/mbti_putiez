package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import putiez.mbti_putiez.entity.visitInfo;

@Repository
public interface mariaJPA extends JpaRepository<visitInfo, Long> {
}
