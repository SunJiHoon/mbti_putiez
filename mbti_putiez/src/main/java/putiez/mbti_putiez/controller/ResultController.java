package putiez.mbti_putiez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    @GetMapping("/result")
    public String resultController(Model model) {
        String value = "INFJ";
        model.addAttribute("key", "../assets/" + value + ".png");
        //src="../assets/${key}.png"
        return "/results/results.html";
    }

    @PostMapping("/resultTest")
    public String yourHandler(@RequestParam("question") String question, Model model) {
        // 모델에 값을 추가
        model.addAttribute("question", question);
        return "resultTest.html"; // result.html을 반환
    }
}
