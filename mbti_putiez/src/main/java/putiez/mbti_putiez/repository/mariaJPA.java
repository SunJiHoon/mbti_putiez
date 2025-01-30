package putiez.mbti_putiez.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import putiez.mbti_putiez.entity.visitCountInfo;
import putiez.mbti_putiez.entity.visitInfo;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface mariaJPA extends JpaRepository<visitInfo, Long> {
    List<visitInfo> findByMbti(String mbti);

    // 만료된 데이터를 업데이트하는 쿼리
    @Transactional
    @Modifying
    @Query("UPDATE visitInfo v SET v.department = 'expired', v.mbti = 'expired' WHERE v.createtime <= :oneYearAgo AND v.department <> 'consent_no_department' AND v.department <> 'expired'")
    int updateExpiredRecords(Timestamp oneYearAgo);
}
