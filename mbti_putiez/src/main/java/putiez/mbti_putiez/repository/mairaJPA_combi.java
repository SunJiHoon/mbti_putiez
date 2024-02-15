package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import putiez.mbti_putiez.entity.CombinationPuang;
import putiez.mbti_putiez.entity.visitInfo;

import java.util.List;

@Repository

public interface mairaJPA_combi extends JpaRepository<CombinationPuang, Long>{
    List<CombinationPuang> findByMbtiAndStatus(String mbti, String status);
}
