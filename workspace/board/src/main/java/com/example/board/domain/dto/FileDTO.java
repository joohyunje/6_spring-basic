package com.example.board.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class FileDTO {

    private Long fileId;
    // 오리지날이 실제 파일 명
    // 게시판 상세보기 페이지에서 실제 노출할 이름
    private String originalFileName;
    // 경로, 우리가 다운로드를 진행할 때
    private String storedFileName;
    private Long fileSize;
    private LocalDateTime uploadTime;
    // FK
    private Long boardId;
}
