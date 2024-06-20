package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardDetailDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
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

    // 게시글 상세보기
    BoardDetailDTO getBoardById(Long boardId, CustomOAuth2User customOAuth2User);

    // 게시글 업데이트로 이동할 때 가지고 갈 board select
    BoardDetailDTO goUpdateBoard(Long boardId);

    // 게시글 업데이트
    // 수정하기를 누르면, 첨부파일은 초기화가 되게끔 구현
    void updateBoard(BoardDTO board, List<MultipartFile> files);

    // 첨부파일 추가하는 메소드
    void saveFile(Long boardId, List<MultipartFile> files);

    // 삭제하기
    void deleteBoard(Long boardId);

}
