package putiez.mbti_putiez.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import putiez.mbti_putiez.repository.MariaJPA_ShareInfo;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class ShareController {
    MariaJPA_ShareInfo mariaJPAShareInfo;
    public ShareController(MariaJPA_ShareInfo mariaJPAShareInfo) {
        this.mariaJPAShareInfo = mariaJPAShareInfo;
    }

    @GetMapping("/sharing/share")
    public String sharePage(@RequestParam("mbti") String mbti, @RequestParam("uuid") String uuid, Model model) {
        boolean check1, check2; //각각 db 내에 해당 mbti에 해당하는 key가 있는지, 그 key가 24시간 내의 링크인지 체크하는 변수

        if () {
            check1 = true;
        } else {
            check1 = false;
        }

        LocalDateTime adjustedTime = LocalDateTime.now().plusHours(9);
        if () {
            check2 = true;
        } else {
            check2 = false;
        }

        if (check1 && check2) {
            model.addAttribute("name", mbti);
            return "/sharing/share";
        } else {
            return "";
        }

    }
}
