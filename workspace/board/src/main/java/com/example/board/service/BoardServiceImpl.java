package com.example.board.service;

import com.example.board.domain.dto.BoardListDTO;
import com.example.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardListDTO> selectPaging(int pageNo, int pageSize) {

        int startRow = (pageNo - 1) * pageSize;
        int endRow = pageNo * pageSize;

        return boardMapper.selectAll(startRow, endRow);
    }

    @Override
    public int countBoard() {
        return boardMapper.countBoard();
    }
}
