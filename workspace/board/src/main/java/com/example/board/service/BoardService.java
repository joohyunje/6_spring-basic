package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardDetailDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.util.PagedResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BoardService {

    // 페이징 처리된 게시글 목록
    List<BoardListDTO> getBoardList(int page, int pageSize);

    // 게시판 총 갯수
    // 페이징 처리할 때 사용할 쿼리
    int getBoardListCount();

    // 게시글 작성
    // 첨부파일도 insert
    void saveBoard(BoardDTO board, List<MultipartFile> files);

    // 게시글 상세보기
    BoardDetailDTO getBoardById(Long boardId, CustomOAuth2User customOAuth2User);

    // 게시글 업데이트로 이동할 때 가지고 갈 board select
    BoardDetailDTO goUpdateBoard(Long boardId);

    // 게시글 업데이트
    // 수정하기를 누르면, 첨부파일은 초기화가 되게끔 구현.
    void updateBoard(BoardDTO board, List<MultipartFile> files);

    // 첨부파일 추가하는 메소드
    void saveFile(Long boardId, List<MultipartFile> files);

    // 게시글 삭제
    void deleteBoard(Long boardId);

    // 최신순
    PagedResponse<BoardListDTO> selectAllByDateDESC(int page, int pageSize);

    // 오래된 순
    PagedResponse<BoardListDTO> selectAllByDateASC(int page, int pageSize);

    // 조회순
    PagedResponse<BoardListDTO> selectAllByViews(int page, int pageSize);

    // 동적 쿼리
    PagedResponse<BoardListDTO> selectD(int page, int pageSize, String sort, String searchType, String search);
}