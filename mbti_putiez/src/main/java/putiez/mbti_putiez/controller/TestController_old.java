package putiez.mbti_putiez.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController_old {
    //api.key.KAKAO_API_KEY
    @Value("${api.key.KAKAO_API_KEY}")
    private String kakaoapikey;

    @GetMapping("/test")
    public String showTest(Model model){
        model.addAttribute("kakaoapikey", kakaoapikey);
        //log.info(kakaoapikey);

        return "test/test";
    }

}
