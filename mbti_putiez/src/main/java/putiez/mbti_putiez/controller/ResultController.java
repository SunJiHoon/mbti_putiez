package putiez.mbti_putiez.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ResultController {
    @PostMapping ("/result")
    public String resultController(@RequestBody MBTIDTO mbtiDTO, Model model) {
        String[] mbtiElements = mbtiDTO.getMbtiElements();
        log.info(Arrays.toString(mbtiElements));

        //mbtiElements 안에는 ["x", "x", "x","x", "x", "x","x", "x", "x","x", "x", "x"] 가 있다.

        int i;
        String value = "";
        value = getString(mbtiElements, value, 0, "E", "I");
        value = getString(mbtiElements, value, 3, "N", "S");
        value = getString(mbtiElements, value, 6, "T", "F");
        value = getString(mbtiElements, value, 9, "J", "P");

        log.info(value);

        model.addAttribute("key", "../assets/" + value + ".png");
        //src="../assets/${key}.png"
        return "redirect:/result";
    }

    private static String getString(String[] mbtiElements, String value, int start, String first, String second) {
        int i;
        int count1 = 0;
        int count2 = 0;
        for (i = start; i < start + 3; i++) {
            if (mbtiElements[i].equals(first)) {
                count1++;
            }
            else {
                count2++;
            }
        }

        if (count1 > count2) {
            value += first;
        }
        else {
            value += second;
        }
        return value;
    }

    @GetMapping("/result")
    public String showResultPage() {
        // 결과 페이지 보여주기
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


