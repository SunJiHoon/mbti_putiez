package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import putiez.mbti_putiez.entity.ShareInfo;
import putiez.mbti_putiez.entity.puangMBTI;

import java.util.Optional;

public interface MariaJPA_ShareInfo extends JpaRepository<ShareInfo, Long> {
    Optional<ShareInfo> findByMbtiAndUuid(String mbti, String uuid);

}
