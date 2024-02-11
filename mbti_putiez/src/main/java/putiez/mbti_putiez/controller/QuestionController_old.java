package putiez.mbti_putiez.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class QuestionController_old {

    @GetMapping("/questions/question1.html")
    public String NextQuestion() {
        return "questions/question1";
    }
}
