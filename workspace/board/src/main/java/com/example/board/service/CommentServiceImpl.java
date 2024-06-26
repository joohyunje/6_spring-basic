package com.example.board.service;

import com.example.board.domain.dto.CommentListDTO;
import com.example.board.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentListDTO> getCommentById(Long boardId) {
        return commentMapper.selectCommentById(boardId);
    }
}
