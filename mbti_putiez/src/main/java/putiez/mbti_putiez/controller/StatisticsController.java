package putiez.mbti_putiez.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import putiez.mbti_putiez.repository.mairaJPA_combi;
import putiez.mbti_putiez.repository.mariaJPA;
import putiez.mbti_putiez.repository.mariaJPA_puangMBTI;
import putiez.mbti_putiez.repository.mariaJPA_visitCountInfo;

@Controller
public class StatisticsController {

    mariaJPA mariajpa;
    putiez.mbti_putiez.repository.mariaJPA_visitCountInfo mariaJPA_visitCountInfo;
    putiez.mbti_putiez.repository.mairaJPA_combi mairaJPA_combi;
    putiez.mbti_putiez.repository.mariaJPA_puangMBTI mariaJPA_puangMBTI;
    public StatisticsController(mariaJPA mariajpa,
                          mariaJPA_visitCountInfo mariaJPA_visitCountInfo,
                          mairaJPA_combi mairaJPA_combi,
                          mariaJPA_puangMBTI mariaJPA_puangMBTI
    ) {
        this.mariajpa = mariajpa;
        this.mariaJPA_visitCountInfo = mariaJPA_visitCountInfo;
        this.mairaJPA_combi = mairaJPA_combi;
        this.mariaJPA_puangMBTI = mariaJPA_puangMBTI;
    }

    @GetMapping("/statistics")
    public String statisticsController() {
        return "statistics/statistics";
    }


}
