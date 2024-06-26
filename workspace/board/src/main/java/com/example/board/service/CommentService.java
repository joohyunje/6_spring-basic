package com.example.board.service;

import com.example.board.domain.dto.CommentListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentListDTO> getCommentById(Long boardId);

}
