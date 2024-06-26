package com.example.board.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long commentId;
    private Long boardId;
    private String providerId;
    private String commentContent;
    private LocalDateTime commentRegisterDate;
    private LocalDateTime commentUpdateDate;
}
