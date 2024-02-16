package putiez.mbti_putiez.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class ShareController_api {


    @PostMapping("/sharing")//요청경로 /api/sharing
    public String sharePage() {
        //json객체를 반환해주세요
        //생성된 난수와 mbti정보를 반환해주세요
        return "";
    }
}
