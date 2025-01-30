package putiez.mbti_putiez.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import putiez.mbti_putiez.repository.mariaJPA;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final mariaJPA mariajpa;

    //초 분 시 일 월 요일
    //0초 0분 3시 매일
    @Scheduled(cron = "0 0 3 * * ?") // 매일 새벽 3시에 실행
    @Transactional
    public void updateExpiredRecords() {
        Timestamp oneYearAgo = Timestamp.from(Instant.now().minusSeconds(365L * 24 * 60 * 60)); // 1년 전

        int updatedCount = mariajpa.updateExpiredRecords(oneYearAgo);
        log.info("만료된 레코드 업데이트 완료: " + updatedCount + "개");
    }

}
