package putiez.mbti_putiez.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import putiez.mbti_putiez.repository.mariaJPA;
import putiez.mbti_putiez.repository.mariaJPA_visitCountInfo;

@Slf4j
@Service
public class EventService {
    mariaJPA mariaJPA;
    mariaJPA_visitCountInfo mariaJPA_visitCountInfo;

    public EventService(putiez.mbti_putiez.repository.mariaJPA mariaJPA,
                        putiez.mbti_putiez.repository.mariaJPA_visitCountInfo mariaJPA_visitCountInfo) {
        this.mariaJPA = mariaJPA;
        this.mariaJPA_visitCountInfo = mariaJPA_visitCountInfo;
    }

    public boolean is5000thResult() {
        return mariaJPA_visitCountInfo.count() == 5000;
    }
}
