package putiez.mbti_putiez.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import putiez.mbti_putiez.entity.CombinationPuang;
import putiez.mbti_putiez.entity.ShareInfo;
import putiez.mbti_putiez.repository.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class ShareController {
    mariaJPA mariajpa;
    MariaJPA_ShareInfo mariaJPAShareInfo;
    mariaJPA_visitCountInfo mariaJPA_visitCountInfo;
    mairaJPA_combi mairaJPA_combi;
    mariaJPA_puangMBTI mariaJPA_puangMBTI;
    public ShareController(mariaJPA mariajpa,
                          mariaJPA_visitCountInfo mariaJPA_visitCountInfo,
                          mairaJPA_combi mairaJPA_combi,
                          mariaJPA_puangMBTI mariaJPA_puangMBTI,
                           MariaJPA_ShareInfo mariaJPAShareInfo
    ) {
        this.mariajpa = mariajpa;
        this.mariaJPA_visitCountInfo = mariaJPA_visitCountInfo;
        this.mairaJPA_combi = mairaJPA_combi;
        this.mariaJPA_puangMBTI = mariaJPA_puangMBTI;
        this.mariaJPAShareInfo = mariaJPAShareInfo;
    }


    @GetMapping("/sharing/share")
    public String sharePage(@RequestParam("mbti") String mbti, @RequestParam("key") String uuid, Model model) {
        boolean check1, check2; //각각 db 내에 해당 mbti에 해당하는 key가 있는지, 그 key가 24시간 내의 링크인지 체크하는 변수
        ShareInfo shareInfo = mariaJPAShareInfo.findByMbtiAndUuid(mbti, uuid).get();

        if (shareInfo.getUuid() != null) {
            check1 = true;
        } else {
            check1 = false;
        }

        LocalDateTime adjustedTime = LocalDateTime.now().plusHours(9);
        LocalDateTime timestamp = shareInfo.getCreateTime().toLocalDateTime();

        Duration duration = Duration.between(timestamp, adjustedTime);
        long hoursDifference = Math.abs(duration.toHours()); // 시간 단위로 변환하여 절대값 취함

        if (hoursDifference <= 24) {
            check2 = true;
        } else {
            check2 = false;
        }

        if (check1 && check2) {
            model.addAttribute("name", mbti);
            model.addAttribute("value", mbti);
            model.addAttribute("key", "/assets/" + mbti + ".png");

            //잘맞는 푸앙이
            List<CombinationPuang> goodpuang_mbtis = mairaJPA_combi.findByMbtiAndStatus(mbti, "good");
            String goodpuang_mbtis_1 = goodpuang_mbtis.get(0).getCombinationPuangMBTI();
            model.addAttribute("goodpuang1", mariaJPA_puangMBTI.findByMbti(goodpuang_mbtis_1).get().getExplanation());
            model.addAttribute("goodpuang1_src", "../assets/" + goodpuang_mbtis_1 +".png");
            String goodpuang_mbtis_2 = goodpuang_mbtis.get(1).getCombinationPuangMBTI();
            model.addAttribute("goodpuang2", mariaJPA_puangMBTI.findByMbti(goodpuang_mbtis_2).get().getExplanation());
            model.addAttribute("goodpuang2_src", "../assets/" + goodpuang_mbtis_2 +".png");


            //안맞는 푸앙이
            List<CombinationPuang> badpuang_mbtis = mairaJPA_combi.findByMbtiAndStatus(mbti, "bad");
            if (!badpuang_mbtis.isEmpty()){
                String badpuang_mbtis_1 = badpuang_mbtis.get(0).getCombinationPuangMBTI();
                model.addAttribute("badpuang1", mariaJPA_puangMBTI.findByMbti(badpuang_mbtis_1).get().getExplanation());
                model.addAttribute("badpuang1_src", "/assets/" + badpuang_mbtis_1 +".png");
                String badpuang_mbtis_2 = badpuang_mbtis.get(1).getCombinationPuangMBTI();
                model.addAttribute("badpuang2", mariaJPA_puangMBTI.findByMbti(badpuang_mbtis_2).get().getExplanation());
                model.addAttribute("badpuang2_src", "/assets/" + badpuang_mbtis_2 +".png");
            }
            return "/sharing/share";
        } else {
            return "expired";
        }

    }
}
