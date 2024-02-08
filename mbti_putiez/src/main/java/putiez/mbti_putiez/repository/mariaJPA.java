package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import putiez.mbti_putiez.entity.visitInfo;

public interface mariaJPA extends JpaRepository<visitInfo, Long> {
}
