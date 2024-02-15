package putiez.mbti_putiez.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import putiez.mbti_putiez.entity.visitCountInfo;
import putiez.mbti_putiez.entity.visitInfo;
import putiez.mbti_putiez.repository.mariaJPA;
import putiez.mbti_putiez.repository.mariaJPA_visitCountInfo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class RCExistignForm {
    mariaJPA mariajpa;
    mariaJPA_visitCountInfo mariaJPA_visitCountInfo;
    public RCExistignForm(mariaJPA mariajpa, mariaJPA_visitCountInfo mariaJPA_visitCountInfo) {
        this.mariajpa = mariajpa;
        this.mariaJPA_visitCountInfo = mariaJPA_visitCountInfo;
    }


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

        if (consent.equals("yes")){
            visitInfo visitinfo = new visitInfo();
            visitinfo.setMbti(value);
            visitinfo.setDepartment(department);
            LocalDateTime adjustedTime = LocalDateTime.now().plusHours(9);//aws상 표준시간+9 필요함.
            visitinfo.setCreatetime(Timestamp.valueOf(adjustedTime));
            //visitinfo.setCreatetime(new Timestamp(System.currentTimeMillis()));
            mariajpa.save(visitinfo);

            visitCountInfo visitCountInfo =mariaJPA_visitCountInfo.findByVisitName("consent_yes");
            if (visitCountInfo != null) {
                // 이미 있는 경우 count를 1 증가
                visitCountInfo.setVisitCount(visitCountInfo.getVisitCount() + 1);
            } else {
                // 새로운 객체를 생성하여 추가
                visitCountInfo = new visitCountInfo();
                visitCountInfo.setVisitName("consent_yes");
                visitCountInfo.setVisitCount(1L);
            }
            visitCountInfo.setLastModified(Timestamp.valueOf(adjustedTime));
            mariaJPA_visitCountInfo.save(visitCountInfo);
        }
        else{
            visitInfo visitinfo = new visitInfo();
            visitinfo.setMbti("consent_no_mbti");
            visitinfo.setDepartment("consent_no_department");
            LocalDateTime adjustedTime = LocalDateTime.now().plusHours(9);//aws상 표준시간+9 필요함.
            visitinfo.setCreatetime(Timestamp.valueOf(adjustedTime));
            //visitinfo.setCreatetime(new Timestamp(System.currentTimeMillis()));
            mariajpa.save(visitinfo);

            visitCountInfo visitCountInfo =mariaJPA_visitCountInfo.findByVisitName("consent_no");
            if (visitCountInfo != null) {
                // 이미 있는 경우 count를 1 증가
                visitCountInfo.setVisitCount(visitCountInfo.getVisitCount() + 1);
            } else {
                // 새로운 객체를 생성하여 추가
                visitCountInfo = new visitCountInfo();
                visitCountInfo.setVisitName("consent_no");
                visitCountInfo.setVisitCount(1L);
            }
            visitCountInfo.setLastModified(Timestamp.valueOf(adjustedTime));
            mariaJPA_visitCountInfo.save(visitCountInfo);

        }

        String mbti_ex1, mbti_ex2, mbti_ex3, mbti_name;
        int order = 0;

        mbti_name = makeName(value);
        
        mbti_ex1 = makeExplain(value, order);
        order++;
        mbti_ex2 = makeExplain(value, order);
        order++;
        mbti_ex3 = makeExplain(value, order);

        model.addAttribute("name", mbti_name);
        model.addAttribute("key", "../assets/" + value + ".png");
        model.addAttribute("explain1", mbti_ex1);
        model.addAttribute("explain2", mbti_ex2);
        model.addAttribute("explain3", mbti_ex3);
        //return "/results/results.html";
        return "results/results";
    }

    private String makeName(String value) {
        String name = "";
        HashMap<String, String> names = new HashMap<>();
        
        names.put("ENFJ", "동물사랑 푸앙"); names.put("ENFP", "반짝 푸앙"); names.put("ENTJ", "학잠 푸앙"); names.put("ENTP", "장난 푸앙");
        names.put("ESFJ", "나만믿어 푸앙"); names.put("ESFP", "반팔 푸앙"); names.put("ESTJ", "안경 푸앙"); names.put("ESTP", "모험 푸앙");
        names.put("INFJ", "돕바 푸앙"); names.put("INFP", "감성 푸앙"); names.put("INTJ", "실험복 푸앙"); names.put("INTP", "후드티 푸앙");
        names.put("ISFJ", "친절 푸앙"); names.put("ISFP", "맨투맨 푸앙"); names.put("ISTJ", "셔츠 푸앙"); names.put("ISTP", "귀찮 푸앙");

        name = names.get(value);
        
        return name;
    }

    private static String makeExplain(String value, int order) {
        String explain = "";
        HashMap<String, String[]> expalins = new HashMap<>();

        expalins.put("ENFJ", new String[]
                {"온화하고 사람들을 좋아하는 푸앙이야! 동물과 어린 아이들도 다 좋아!", "동정심이 많고 이타적이야. 리더십이 있어서 계획적으로 집단을 잘 이끌어!", "베푸는 것을 좋아해서 힘들어하는 사람을 도와주고자 해."});
        expalins.put("ENFP", new String[]
                {"새로운 것을 좋아하는 푸앙이야! 활기차고 적극적이야.", "처음 보는 사람과도 잘 어울리고 친해지려고 해!", "좋아하는 일에는 열정을 쏟지만, 반복되는 일은 힘들어…"});
        expalins.put("ENTJ", new String[]
                {"타고난 리더 재질의 푸앙이야! 확고한 신념을 가지고 있어.", "목표를 이루기 위해 계획 세우는 것을 즐겨. 논리적이고 주도적이야!", "효율성을 중시해! 생산적이지 않는 것은 싫어."});
        expalins.put("ENTP", new String[]
                {"장난스럽고 낙천적인 푸앙이야! 분위기를 주도하는 역할을 해.", "내 맘에 안들면 다 갈아 엎고 싶어. 엉뚱한 생각도 많이 해!", "논쟁과 토론도 좋아해! 나 자신에 대한 자신감이 있어."});
        expalins.put("ESFJ", new String[]
                {"사교적이고 사람들을 돕고자 하는 푸앙이야! 사회성이 좋고 정이 많아.", "붙임성이 좋아서 새로운 환경에 적응을 잘 해.", "상대방을 잘 배려해주고 공감해주는 경향이 있어!"});
        expalins.put("ESFP", new String[]
                {"밝고 긍정적인 푸앙이야! 낙천적이고 활동적이야.", "사람들에게 주목 받는 것도 좋고, 유행을 따르는 것도 좋아!", "앉아서 하는 활동보다는 몸으로 하는 체험 활동을 좋아해!"});
        expalins.put("ESTJ", new String[]
                {"논리적으로 사람들을 주도하는 푸앙이야! 리더십이 있어서 조직을 잘 이끌어.", "현실적이고 계획적이라서 자신의 할 일을 잘 수행하는 능력이 있어!", "성실하고 꼼꼼한 성격으로 질서 있는 환경을 좋아해."});
        expalins.put("ESTP", new String[]
                {"삶을 즐기고 모험을 좋아하는 푸앙이야! 센스와 유머 감각도 있어.", "사람들과 어울리는 게 좋아! 긴 글은 읽기 힘들어…", "빠르게 판단하고 현실적이야. 스릴 있고 오감을 자극하는 게 좋아!"});
        expalins.put("INFJ", new String[]
                {"돕바 속에 따뜻함을 감추고 있는 푸앙이야! 인내심이 많고 도덕적이야.", "성숙하고 사람들을 잘 배려해. 창의적인 생각으로 세상을 바꾸고 싶어해!", "신중하지만 가끔씩은 걱정이 너무 많아서 힘들어…"});
        expalins.put("INFP", new String[]
                {"공감을 잘하는 감성적인 푸앙이야! 자신만의 신념이 강해.", "처음엔 낯을 많이 가리지만 친한 사람에게는 본모습을 드러내는 편이야.", "창의성과 상상력이 뛰어나! 흥미 있거나 의미 있는 일을 하고 싶어해."});
        expalins.put("INTJ", new String[]
                {"창의적이고 상상력이 풍부한 푸앙이야! 논리적이고 합리적인 것을 좋아해.", "완벽주의 성향이 있고, 미래와 목표를 지향해.", "독립적이고 논리를 중요시해! 상상도 많이 하는 편이야."});
        expalins.put("INTP", new String[]
                {"논리적이고 분석적인 푸앙이야! 과학적이고 객관적인 것이 좋아.", "먼저 대화를 시작하지는 않지만, 관심있는 분야에 대해서는 말이 많아져!", "사람들을 만나는 것보다는 혼자 활동하는 것을 더 좋아해."});
        expalins.put("ISFJ", new String[]
                {"차분하고 누구에게나 친절한 푸앙이야! 동정심이 많고 사려 깊어.", "상대방의 감정을 쉽게 파악해. 겸손한 성격이라 대인 관계가 좋아!", "계획적이지만 원하는 결과가 나오지 않으면 스트레스를 받아…"});
        expalins.put("ISFP", new String[]
                {"다정하고 온화한 푸앙이야! 사람들과의 갈등을 좋아하지 않아.", "예술 분야에 관심이 많아. 도덕적이고 봉사 직종에도 잘 어울려!", "거절이나 싫은 소리를 잘 못해! 욕심이 없어서 소확행을 즐기는 것을 좋아해."});
        expalins.put("ISTJ", new String[]
                {"책임감이 있고 신중한 푸앙이야! 침착하고 현실적인 성격을 가졌어.", "집중력이 좋고 계획적이라서 자신의 임무를 잘 수행해.", "원리원칙을 잘 지켜! 나만의 루틴이 깨지면 스트레스를 받아…"});
        expalins.put("ISTP", new String[]
                {"논리적이고 효율을 추구하는 푸앙이야! 필요 이상으로는 노력하고 싶지 않아 해.", "타인에게 무관심하고 공감을 잘 못해… 그렇지만 인간 관계는 좋은 편이야!", "자유롭고 개방적이야! 인과관계와 객관적 원리에 관심이 많아."});

        explain = expalins.get(value)[order];
        return explain;
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
