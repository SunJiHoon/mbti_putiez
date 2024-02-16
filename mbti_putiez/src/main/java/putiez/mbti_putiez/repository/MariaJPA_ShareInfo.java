package putiez.mbti_putiez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import putiez.mbti_putiez.entity.ShareInfo;
import putiez.mbti_putiez.entity.puangMBTI;

public interface MariaJPA_ShareInfo extends JpaRepository<ShareInfo, Long> {

}
