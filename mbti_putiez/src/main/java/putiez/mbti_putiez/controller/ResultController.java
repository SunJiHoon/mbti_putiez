package putiez.mbti_putiez.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ResultController {
    @GetMapping("/result")
    public String resultController(@RequestBody MBTIDTO mbtiDTO, Model model) {
        String[] mbtiElements = mbtiDTO.getMbtiElements();
        log.info(Arrays.toString(mbtiElements));

        //mbtiElements 안에는 ["x", "x", "x","x", "x", "x","x", "x", "x","x", "x", "x"] 가 있다.

        String value = "INFJ";
        model.addAttribute("key", "../assets/" + value + ".png");
        //src="../assets/${key}.png"
        return "/results/results.html";
    }
    @PostMapping("/resultTest")
    public String yourHandler(@RequestBody MBTIDTO mbtiDTO) {
        // mbtiDTO에서 mbtiElements 값을 사용하여 요청 처리
        String[] mbtiElements = mbtiDTO.getMbtiElements();
        log.info(Arrays.toString(mbtiElements));
        // 필요한 로직 수행
        return "resultTest.html"; // 결과 페이지로 이동
    }
}

@Data
class MBTIDTO {
    private String[] mbtiElements;
}
