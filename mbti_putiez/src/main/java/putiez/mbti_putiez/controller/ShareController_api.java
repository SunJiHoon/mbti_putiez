package putiez.mbti_putiez.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
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
    //public Result sharePage(@RequestBody String value) {
    public Result sharePage(@RequestBody StringValue stringValue) {
        String value = stringValue.getValue();
            log.info(value);
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.setMbti(value);

        Result result = new Result();
        //json객체를 반환해주세요
        //생성된 난수와 mbti정보를 반환해주세요

        UUID uuid = UUID.randomUUID();
        LocalDateTime adjustedTime = LocalDateTime.now().plusHours(9);//aws상 표준시간+9 필요함.
        shareInfo.setCreateTime(Timestamp.valueOf(adjustedTime));

        // UUID를 문자열로 변환
        String randomUUIDString = uuid.toString();

        // 문자열에서 '-' 제거
        String randomNumberString = randomUUIDString.replaceAll("-", "");

        try {
            //에러 검증 내용을 여기에 적어주세요.
            result.setStatus("available");
        } catch (Exception e) {
            result.setStatus("unavailable");
        }
        result.setUrl("mbti.putiez.com/sharing/share?mbti="+ value + "&key=" + randomNumberString);
        shareInfo.setStatus(result.getStatus());
        shareInfo.setUuid(randomNumberString);
        mariaJPAShareInfo.save(shareInfo);
        return result;
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
    @GetMapping("/sharing-test2")
    public ShareInfo asfasdfasdf(){
        Optional<ShareInfo> shareInfoOptional = mariaJPAShareInfo.findByMbtiAndUuid("ISFP","044d8c7b88264798b55f5613e0dd3c01");
        ShareInfo shareInfo = shareInfoOptional.get();
        log.info(shareInfo.getCreateTime().toString());
        return shareInfo;
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

@Data
class StringValue{
    private String value;
}