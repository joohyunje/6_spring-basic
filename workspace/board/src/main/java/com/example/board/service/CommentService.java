package com.example.board.service;

import com.example.board.domain.dto.CommentDTO;
import com.example.board.domain.dto.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentListDTO> getCommentById(Long boardId);

    void saveComment(CommentDTO commentDTO);

    void deleteComment(Long commentId);

    void updateComment(CommentDTO commentDTO);

}
