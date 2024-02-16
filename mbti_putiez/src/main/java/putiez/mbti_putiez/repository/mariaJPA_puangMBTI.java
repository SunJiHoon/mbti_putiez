package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import putiez.mbti_putiez.entity.CombinationPuang;
import putiez.mbti_putiez.entity.puangMBTI;
import putiez.mbti_putiez.entity.visitCountInfo;

import java.util.List;
import java.util.Optional;

@Repository

public interface mariaJPA_puangMBTI extends JpaRepository<puangMBTI, Long> {
//    List<CombinationPuang> findByMbtiAndStatus(String mbti, String status);
    Optional<puangMBTI> findByMbti(String mbti);
}
