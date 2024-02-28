package putiez.mbti_putiez.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import putiez.mbti_putiez.entity.Percent;
import putiez.mbti_putiez.entity.visitInfo;
import putiez.mbti_putiez.repository.mairaJPA_combi;
import putiez.mbti_putiez.repository.mariaJPA;
import putiez.mbti_putiez.repository.mariaJPA_puangMBTI;
import putiez.mbti_putiez.repository.mariaJPA_visitCountInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsMakingService {

    mariaJPA mariajpa;
    putiez.mbti_putiez.repository.mariaJPA_visitCountInfo mariaJPA_visitCountInfo;
    putiez.mbti_putiez.repository.mairaJPA_combi mairaJPA_combi;
    putiez.mbti_putiez.repository.mariaJPA_puangMBTI mariaJPA_puangMBTI;
    public StatisticsMakingService(mariaJPA mariajpa,
                                putiez.mbti_putiez.repository.mariaJPA_visitCountInfo mariaJPA_visitCountInfo,
                                putiez.mbti_putiez.repository.mairaJPA_combi mairaJPA_combi,
                                putiez.mbti_putiez.repository.mariaJPA_puangMBTI mariaJPA_puangMBTI
    ) {
        this.mariajpa = mariajpa;
        this.mariaJPA_visitCountInfo = mariaJPA_visitCountInfo;
        this.mairaJPA_combi = mairaJPA_combi;
        this.mariaJPA_puangMBTI = mariaJPA_puangMBTI;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public Map<String, Double> makeStatistics() {
        //mariaJPA_visitCountInfo에서 학과 정보, mbti 정보 등을 가져와서
        List<visitInfo> visitInfoList_INFJ = mariajpa.findByMbti("INFJ");
        List<visitInfo> visitInfoList_INFP = mariajpa.findByMbti("INFP");
        List<visitInfo> visitInfoList_INTJ = mariajpa.findByMbti("INTJ");
        List<visitInfo> visitInfoList_INTP = mariajpa.findByMbti("INTP");
        List<visitInfo> visitInfoList_ISFJ = mariajpa.findByMbti("ISFJ");
        List<visitInfo> visitInfoList_ISFP = mariajpa.findByMbti("ISFP");
        List<visitInfo> visitInfoList_ISTJ = mariajpa.findByMbti("ISTJ");
        List<visitInfo> visitInfoList_ISTP = mariajpa.findByMbti("ISTP");

        int size_INFJ = visitInfoList_INFJ.size();
        int size_INFP = visitInfoList_INFP.size();
        int size_INTJ = visitInfoList_INTJ.size();
        int size_INTP = visitInfoList_INTP.size();
        int size_ISFJ = visitInfoList_ISFJ.size();
        int size_ISFP = visitInfoList_ISFP.size();
        int size_ISTJ = visitInfoList_ISTJ.size();
        int size_ISTP = visitInfoList_ISTP.size();

        List<visitInfo> visitInfoList_ENFJ = mariajpa.findByMbti("ENFJ");
        List<visitInfo> visitInfoList_ENFP = mariajpa.findByMbti("ENFP");
        List<visitInfo> visitInfoList_ENTJ = mariajpa.findByMbti("ENTJ");
        List<visitInfo> visitInfoList_ENTP = mariajpa.findByMbti("ENTP");
        List<visitInfo> visitInfoList_ESFJ = mariajpa.findByMbti("ISFJ");
        List<visitInfo> visitInfoList_ESFP = mariajpa.findByMbti("ESFP");
        List<visitInfo> visitInfoList_ESTJ = mariajpa.findByMbti("ESTJ");
        List<visitInfo> visitInfoList_ESTP = mariajpa.findByMbti("ESTP");

        int size_ENFJ = visitInfoList_ENFJ.size();
        int size_ENFP = visitInfoList_ENFP.size();
        int size_ENTJ = visitInfoList_ENTJ.size();
        int size_ENTP = visitInfoList_ENTP.size();
        int size_ESFJ = visitInfoList_ESFJ.size();
        int size_ESFP = visitInfoList_ESFP.size();
        int size_ESTJ = visitInfoList_ESTJ.size();
        int size_ESTP = visitInfoList_ESTP.size();

        int entireSize = size_ENFJ + size_ENFP + size_ENTJ + size_ENTP + size_ESFJ + size_ESFP + size_ESTP + size_ESTJ
                + size_INFJ + size_INFP + size_INTJ + size_ISFP + size_ISTJ + size_ISTP + size_ISFJ + size_INTP;

        //정보를 가공
        //가공된 자료 (mbti %정보)
        //PerctenRepo에 삽입, 혹은 업데이트
/*

        //List<Percent> entities = putiez.mbti_putiez.repository.mariaJPA_visitCountInfo.findAll();

        // MBTI 유형별 빈도 계산
        Map<String, Long> countByMBTI = entities.stream()
                .collect(Collectors.groupingBy(entity -> entity.getMbti(), Collectors.counting()));

        // 총 응답 수 계산
        long totalResponses = entities.size();

        // 백분율 계산
        return countByMBTI.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ((double) entry.getValue() / totalResponses) * 100
                ));

 */
    }
}
