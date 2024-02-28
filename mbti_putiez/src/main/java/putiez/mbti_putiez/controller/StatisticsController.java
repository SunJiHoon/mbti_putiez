package putiez.mbti_putiez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

    @GetMapping("/statistics")
    public String statisticsController() {
        return "statistics/statistics";
    }
}
