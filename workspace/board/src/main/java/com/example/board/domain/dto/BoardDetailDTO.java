package com.example.board.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class BoardDetailDTO {

    // pk
    private Long boardId;
    // 제목
    private String boardTitle;
    // 작성자
    private String name;
    // 내용
    private String boardContent;
    // 조회수
    private int boardViews;
    // 작성일
    private LocalDateTime boardRegisterDate;
    // 수정일
    private LocalDateTime boardUpdateDate;
    // 첨부파일의 갯수
    private int fileCount;
    // 작성자 pk
    private String providerId;

}
