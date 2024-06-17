package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardListDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BoardService {

    // 페이징 처리할 때 사용할 쿼리
    List<BoardListDTO> selectPaging(int pageNo, int pageSize);

    // 게시판 총 갯수
    // 페이징 처리할 때 사용할 쿼리
    int countBoard();

    // 게시글 작성
    // 첨부파일도 insert
    void saveBoard(BoardDTO board, List<MultipartFile> files);

}
