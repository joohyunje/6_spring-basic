package com.example.board.service;

import com.example.board.domain.dto.CommentDTO;
import com.example.board.domain.dto.CommentListDTO;
import com.example.board.domain.vo.CommentVO;
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

    @Override
    public void saveComment(CommentDTO commentDTO) {
        commentMapper.insertComment(CommentVO.toEntity(commentDTO));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        commentMapper.updateComment(CommentVO.toEntity(commentDTO));
    }
}
