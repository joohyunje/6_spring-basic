package com.example.board.mapper;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardDetailDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시판 목록
    List<BoardListDTO> selectAll(int startRow, int endRow);

    // 게시판 총 갯수
    // 페이징 처리할 때 사용할 쿼리
    int countBoard();

    // 다음 시퀀스 가져오기
    // 게시글 작성 때 사용할 쿼리
    long getSeq();

    // 게시글 작성
    void saveBoard(BoardDTO board);

    // 게시글 상세보기
    BoardDetailDTO selectBoardDetail(Long boardId);

    // 조회 수 +1
    void plusView(Long boardId);

    // 게시글 수정하기
    void updateBoard(BoardVO boardVO);

    // 게시글 삭제하기
    void deleteBoard(Long boardId);

    // 게시글 오래된 순
    List<BoardListDTO> selectAllByDateASC(int startRow, int endRow);

    // 게시글 조회순
    List<BoardListDTO> selectAllByViews(int startRow, int endRow);


    // 동적 쿼리
    List<BoardListDTO> selectD(int startRow, int endRow, String sort, String searchType, String search);

    // 동적 쿼리 시, 게시글 갯수
    int countDBoard(String searchType, String search);
}