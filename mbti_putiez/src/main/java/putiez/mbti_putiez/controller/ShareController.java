package putiez.mbti_putiez.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ShareController {

    @GetMapping("/sharing")
    public String shareController() {
        return "";
    }
}
