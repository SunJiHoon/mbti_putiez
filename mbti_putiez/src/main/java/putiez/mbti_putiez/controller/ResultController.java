package putiez.mbti_putiez.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ResultController {
    @GetMapping("/questions/questions")
    public String resultController() {
        return "questions/questions";
    }
    @GetMapping("/result")
    public String showResultPage(@RequestParam("data") String value, Model model) {
        model.addAttribute("key", "../assets/" + value + ".png");
        return "/results/results.html";
    }

}


