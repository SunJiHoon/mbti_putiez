package putiez.mbti_putiez.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Controller
@Slf4j

public class ResultController_old {
    @PostMapping("/result-old")
    public String resultController(@RequestBody MBTIDTO_old mbtiDTO, Model model) {
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
        return "redirect:/result-old";
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

    @GetMapping("/result-old")
    public String showResultPage() {
        // 결과 페이지 보여주기
        return "/results/results.html";
    }
}
@Data
class MBTIDTO_old {
    private String[] mbtiElements;

}