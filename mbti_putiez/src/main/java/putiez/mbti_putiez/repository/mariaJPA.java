package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import putiez.mbti_putiez.entity.visitCountInfo;
import putiez.mbti_putiez.entity.visitInfo;

import java.util.List;

@Repository
public interface mariaJPA extends JpaRepository<visitInfo, Long> {
    List<visitInfo> findByMbti(String mbti);

}
