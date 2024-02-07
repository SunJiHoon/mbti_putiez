package putiez.mbti_putiez.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

@Controller
@Slf4j
public class RCExistignForm {
    @GetMapping("/privacy-policy")
    public String showPrivacyPolicy(){
        return "privacy/privacy";
    }
    @PostMapping("/questionnaire")
    public String showMeQuestionnaire(@RequestParam(name = "consent") String consent,
                                      @RequestParam(name = "department") String department,
                                      Model model){
        model.addAttribute("consent", consent);
        if(consent.equals("yes")){
            model.addAttribute("department", department);
            return "questions/questions_ver2";
        }
        else{
            model.addAttribute("department", "학과 정보 x");
            return "redirect:/";
        }
    }
    @GetMapping("/questionnaire")
    public String showMeQuestionnaire_directly(Model model) {
        model.addAttribute("consent", "no");
        model.addAttribute("department", "학과 정보 x");
        return "questions/questions_ver2";
    }

    //@GetMapping("/result-existing")
    public String showResultPage() {
        // 결과 페이지 보여주기
        //return "/questions/questions.html";
        return "questions/questions";
    }
    @PostMapping("/result-existing")
    public String resultController(@RequestParam Map<String, String> formData
                                   //@RequestBody MBTIDTO_old mbtiDTO,
                                   ,HttpServletRequest request
            , Model model) {
        //consent정보와 department정보 확인
        String consent = formData.get("consent");
        String department = formData.get("department");
        log.info(consent);
        log.info(department);


        String str = formData.get("mbtiElements");
        log.info(str);

        // 문자열에서 괄호 및 쌍따옴표를 제거하고 쉼표로 구분된 각 요소를 추출
        String[] mbtiElements = str.substring(1, str.length() - 1).split(",");

        // 배열에 각 요소 추가
        for (int i = 0; i < mbtiElements.length; i++) {
            mbtiElements[i] = mbtiElements[i].replaceAll("\"", ""); // 쌍따옴표 제거
        }

        //String[] mbtiElements = ["x", "x", "x","x", "x", "x","x", "x", "x","x", "x", "x"];//mbtiDTO.getMbtiElements();
        //String[] mbtiElements = //new String[]{formData.get("mbtiElements")};//{"x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x"};
        //String[] mbtiElements = mbtiDTO.getMbtiElements();
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
        //return "/results/results.html";
        return "results/results";
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




}

@Data
class MBTIDTO_existing {
    private String[] mbtiElements;

}
