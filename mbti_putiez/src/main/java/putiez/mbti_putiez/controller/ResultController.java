package putiez.mbti_putiez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {
    @GetMapping("/result")
    public String resultController(Model model) {
        String value = "INFJ";
        model.addAttribute("key", value);
        //src="../assets/${key}.png"
        return "/results/results.html";
    }
}
