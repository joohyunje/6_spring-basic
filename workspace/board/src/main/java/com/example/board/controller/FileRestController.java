package com.example.board.controller;

import com.example.board.domain.dto.FileDTO;
import com.example.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@RestController // 해당 클래스를 RESTful 컨트롤러로 표시
@RequestMapping("/download")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @GetMapping("/{fileId}")
    // Resource springframework 에 있는 녀석으로 import
    public ResponseEntity<Resource> download(@PathVariable("fileId") Long fileId) {
        FileDTO file = fileService.getFileById(fileId);

        // 실제 파일 경로 생성
        Path filePath = Path.of(file.getStoredFileName()); // 실제 파일이 젖ㅇ되어있는 경로

        // 해당 경로의 파일을 resource 형태로 로드
        Resource resource = new FileSystemResource(filePath);

        // 리소스가 존재하는지 확인하고, 존재하지 않으면 404 에러 반환.
        if(!resource.exists()){
            return ResponseEntity.notFound().build();
        }

        // 원본 파일 이름을 Content-Disposition 헤더에서 사용할 수 있게끔 인코딩.
        // 파일 이름을 URL 로 인코딩한다.
        // 이는 파일 이름에 공백이나 특수 문자가 포함되어 있을 경우 문제가 발생하지 않도록 하기 위함.
        String encodedFileName = URLEncoder.encode(file.getOriginalFileName(),
                StandardCharsets.UTF_8).replace("+", "%20");

        // 클라이언트가 응답을 어떻게 처리할지를 결정
        // attachment; -> 파일을 다운로드 해라.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
