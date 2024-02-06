package putiez.mbti_putiez.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {

    @GetMapping("/error")
    public String errorController() {
        return "error";
    }
}
