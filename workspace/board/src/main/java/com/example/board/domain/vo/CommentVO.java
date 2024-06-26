package com.example.board.domain.vo;

import com.example.board.domain.dto.CommentDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class CommentVO {

    private Long commentId;
    private Long boardId;
    private String providerId;
    private String commentContent;
    private LocalDateTime commentRegisterDate;
    private LocalDateTime commentUpdateDate;

    @Builder
    public CommentVO(Long commentId, Long boardId, String providerId, String commentContent, LocalDateTime commentRegisterDate, LocalDateTime commentUpdateDate) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.providerId = providerId;
        this.commentContent = commentContent;
        this.commentRegisterDate = commentRegisterDate;
        this.commentUpdateDate = commentUpdateDate;
    }

    public static CommentVO toEntity(CommentDTO commentDTO) {
        return CommentVO.builder().commentId(commentDTO.getCommentId())
                .boardId(commentDTO.getBoardId())
                .providerId(commentDTO.getProviderId())
                .commentContent(commentDTO.getCommentContent())
                .commentRegisterDate(commentDTO.getCommentRegisterDate())
                .commentUpdateDate(commentDTO.getCommentUpdateDate())
                .build();
    }

}
