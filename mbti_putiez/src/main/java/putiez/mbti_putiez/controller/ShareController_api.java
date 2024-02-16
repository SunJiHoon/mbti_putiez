package putiez.mbti_putiez.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api")
public class ShareController_api {

    @PostMapping("/sharing")//요청경로 /api/sharing
    public Result sharePage() {
        Result result = new Result();
        //json객체를 반환해주세요
        //생성된 난수와 mbti정보를 반환해주세요

        UUID uuid = UUID.randomUUID();

        // UUID를 문자열로 변환
        String randomUUIDString = uuid.toString();

        // 문자열에서 '-' 제거
        String randomNumberString = randomUUIDString.replaceAll("-", "");

        // 문자열의 일부를 추출하여 숫자로 변환
        String randomNumberSubstring = randomNumberString.substring(0, 6);
        int randomNumber = Integer.parseInt(randomNumberSubstring);

        result.setRandom(randomNumber);
        result.setMbti("ENFJ");
        return result;
    }

    @Getter @Setter
    static class Result {
        private int random;
        private String mbti;

    }
}