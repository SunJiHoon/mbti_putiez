package putiez.mbti_putiez.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import putiez.mbti_putiez.entity.ShareInfo;
import putiez.mbti_putiez.repository.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class ShareController_api {
    MariaJPA_ShareInfo mariaJPAShareInfo;
    public ShareController_api(MariaJPA_ShareInfo mariaJPAShareInfo
    ) {
        this.mariaJPAShareInfo = mariaJPAShareInfo;
    }

    @PostMapping("/sharing")//요청경로 /api/sharing
    public Result sharePage(@RequestBody String value) {
        log.info(value);
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.setMbti(value);

        Result result = new Result();
        //json객체를 반환해주세요
        //생성된 난수와 mbti정보를 반환해주세요

        UUID uuid = UUID.randomUUID();
        Timestamp timestamp = extractTimestampFromUUID(uuid);
        shareInfo.setCreateTime(timestamp);

        // UUID를 문자열로 변환
        String randomUUIDString = uuid.toString();

        // 문자열에서 '-' 제거
        String randomNumberString = randomUUIDString.replaceAll("-", "");

        // 문자열의 일부를 추출하여 숫자로 변환
        String randomNumberSubstring = randomNumberString.substring(0, 6);
        int randomNumber = Integer.parseInt(randomNumberSubstring);

        try {
            //에러 검증 내용을 여기에 적어주세요.
            result.setStatus("available");
        } catch (Exception e) {
            result.setStatus("unavailable");
        }
        result.setUrl("mbti.putiez.com/sharing/share?mbti="+ value + "&key=" + randomNumber);
        shareInfo.setStatus(result.getStatus());
        mariaJPAShareInfo.save(shareInfo);
        return result;
    }
    public static Timestamp extractTimestampFromUUID(UUID uuid) {
        // UUID의 첫 번째 8자리를 추출하여 16진수로 변환
        String uuidString = uuid.toString();
        String timestampHex = uuidString.substring(0, 8);

        // 16진수를 10진수로 변환하여 타임스탬프를 얻음
        long timestampMillis = Long.parseLong(timestampHex, 16);

        // 타임스탬프를 java.sql.Timestamp 객체로 변환
        return new Timestamp(timestampMillis);
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
class Result {
    private String status;
    private String url;

}

@Data
class UrlAndResult{
    private String result;
    private String url;

}