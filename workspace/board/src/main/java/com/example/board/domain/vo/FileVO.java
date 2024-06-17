package com.example.board.domain.vo;

import com.example.board.domain.dto.FileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@ToString
@NoArgsConstructor
public class FileVO {

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

    @Builder
    public FileVO (Long fileId, String originalFileName, String storedFileName, Long fileSize, LocalDateTime uploadTime, Long boardId) {
        this.fileId = fileId;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.fileSize = fileSize;
        this.uploadTime = uploadTime;
        this.boardId = boardId;
    }

    public static FileVO toEntity(FileDTO fileDTO){
        return FileVO.builder().fileId(fileDTO.getFileId())
                .originalFileName(fileDTO.getOriginalFileName())
                .storedFileName(fileDTO.getStoredFileName())
                .fileSize(fileDTO.getFileSize())
                .boardId(fileDTO.getBoardId())
                .build();
    }


}
