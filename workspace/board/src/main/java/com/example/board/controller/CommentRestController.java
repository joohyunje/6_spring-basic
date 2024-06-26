package com.example.board.controller;

import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
