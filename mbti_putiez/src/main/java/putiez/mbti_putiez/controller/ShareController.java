package putiez.mbti_putiez.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import putiez.mbti_putiez.entity.ShareInfo;
import putiez.mbti_putiez.repository.MariaJPA_ShareInfo;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class ShareController {
    MariaJPA_ShareInfo mariaJPAShareInfo;
    public ShareController(MariaJPA_ShareInfo mariaJPAShareInfo) {
        this.mariaJPAShareInfo = mariaJPAShareInfo;
    }

    @GetMapping("/sharing/share")
    public String sharePage(@RequestParam("mbti") String mbti, @RequestParam("key") String uuid, Model model) {
        boolean check1, check2; //각각 db 내에 해당 mbti에 해당하는 key가 있는지, 그 key가 24시간 내의 링크인지 체크하는 변수
        ShareInfo shareInfo = mariaJPAShareInfo.findByMbtiAndUuid(mbti, uuid).get();

        if (shareInfo.getUuid() != null) {
            check1 = true;
        } else {
            check1 = false;
        }

        LocalDateTime adjustedTime = LocalDateTime.now().plusHours(9);
        LocalDateTime timestamp = shareInfo.getCreateTime().toLocalDateTime();

        Duration duration = Duration.between(timestamp, adjustedTime);
        long hoursDifference = Math.abs(duration.toHours()); // 시간 단위로 변환하여 절대값 취함

        if (hoursDifference <= 24) {
            check2 = true;
        } else {
            check2 = false;
        }

        if (check1 && check2) {
            model.addAttribute("name", mbti);
            model.addAttribute("value", mbti);
            model.addAttribute("key", "/assets/" + mbti + ".png");
            return "/sharing/share";
        } else {
            return "expired";
        }

    }
}
