package putiez.mbti_putiez.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/sharing-test")//요청경로 /api/sharing
    public UrlAndResult sharePage_test(@RequestBody String value) {
//        log.info(value);
        //json객체를 반환해주세요
        //생성된 난수와 mbti정보를 반환해주세요
        UrlAndResult urlAndResult =  new UrlAndResult();
        urlAndResult.setResult("success");
        urlAndResult.setUrl("mbti.putiez.com/sharing/share?"+value + "&key=rand5198132451");
        return urlAndResult;
    }
}

@Data
class UrlAndResult{
    private String result;
    private String url;
}