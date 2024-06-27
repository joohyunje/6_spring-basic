package com.example.board.controller;

import com.example.board.domain.dto.CommentDTO;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentRestController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("/{boarId}")
    public ResponseEntity<?> getComments(@PathVariable Long boarId) { // ? : 리턴타입과 자동으로 맞춰짐
        return ResponseEntity.ok(commentService.getCommentById(boarId));
    }

    //댓글 추가
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO) {
        commentService.saveComment(commentDTO);
        return ResponseEntity.ok().build();
    }

    // 삭제 추가
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setCommentId(commentId);
        commentService.updateComment(commentDTO);

        return ResponseEntity.ok().build();
    }


}
