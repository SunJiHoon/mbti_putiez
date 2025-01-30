package putiez.mbti_putiez.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import putiez.mbti_putiez.repository.mariaJPA;
import putiez.mbti_putiez.repository.mariaJPA_visitCountInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final mariaJPA mariajpa;
//    mariaJPA_visitCountInfo mariaJPA_visitCountInfo;


    public boolean is5000thResult() {
        return mariajpa.count() >= 6;
    }
}
