package putiez.mbti_putiez.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathController_api {
    @Value("${myCurrentUrl}")
    private String path;

    @GetMapping ("/requestPath")
    public String pathController() {
        return path;
    }
}
