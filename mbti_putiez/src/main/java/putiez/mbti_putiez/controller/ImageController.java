package putiez.mbti_putiez.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@Slf4j
public class ImageController {
    @Value("${resourcesPath}")
    private String resourcesPath;

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();
    @GetMapping("/image/{filename}")//image를 웹 상에 띄우는 방식. -카카오톡 이미지를 대체할 방법.
    public ResponseEntity<byte[]> getImage(@PathVariable(name = "filename") String filename) throws IOException {
        // 이미지 파일의 경로
        //String resourcesPath = "D:/projects/putiez_mbtipuang/mbti_putiez/mbti_putiez/src/main/resources/";
        //String imagePath = "static/assets/index_puang.png";
        String imagePath = "static/assets/" + filename;
        // 이미지 파일을 바이트 배열로 읽어오기
        byte[] imageBytes = Files.readAllBytes(Paths.get(resourcesPath + imagePath));

        // 이미지에 대한 헤더 정보 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // 이미지의 미디어 타입 설정
        headers.setContentLength(imageBytes.length); // 이미지의 크기 설정

        // 이미지 데이터와 헤더 정보를 포함한 ResponseEntity 반환
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/file/{filename}")
    @ResponseBody//이미지를 다운받는 방식
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "filename") String filename) throws
            IOException {
        log.info(filename);
        //Resource resource = resourceLoader.getResource("classpath:" + filename);
        Resource resource = resourceLoader.getResource("classpath:static/assets/index_puang.png");
        //"classpath:static/assets/index_puang.png"
        File file = resource.getFile();

        Tika tika = new Tika();
        String mediaType = tika.detect(file);


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE,mediaType)
                .header(HttpHeaders.CONTENT_LENGTH,file.length() + "")
                .body(resource)
                ;
    }
}
