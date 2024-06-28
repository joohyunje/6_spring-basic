package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardDetailDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.dto.FileDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.util.PagedResponse;
import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.FileVO;
import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

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

    @Override
    @Transactional // 해당 메소드를 하나의 트랜잭션으로 묶는다.
    public void saveBoard(BoardDTO board, List<MultipartFile> files) {
        Long boardId = boardMapper.getSeq();
        board.setBoardId(boardId);
        boardMapper.saveBoard(board); // 게시글 정보 저장
        saveFile(boardId, files);


    }

    @Override
    @Transactional
    public BoardDetailDTO getBoardById(Long boardId, CustomOAuth2User customOAuth2User) {
        BoardDetailDTO board = boardMapper.selectBoardDetail(boardId);

        // 조회 수 상승을 셜정할 if
        if(customOAuth2User == null || customOAuth2User.getProviderId().equals(board.getProviderId())) {
            // 조회 수가 플러스 1이 되는 update 쿼리문
            boardMapper.plusView(boardId);
        }

        return board;
    }

    // 수정폼으로 갈때 가져갈 원래 게시글 정보
    @Override
    public BoardDetailDTO goUpdateBoard(Long boardId) {
        return boardMapper.selectBoardDetail(boardId);
    }

    @Override
    public void updateBoard(BoardDTO board, List<MultipartFile> files) {
        boardMapper.updateBoard(BoardVO.toEntity(board));
        // 원래 있던 첨부파일 삭제
        fileMapper.deleteFile(board.getBoardId());
        // 그냥 files insert
        saveFile(board.getBoardId(), files);

    }

    @Override
    public void saveFile(Long boardId, List<MultipartFile> files) {
        // 현재 날짜를 기반으로 폴더 경로 생성
        LocalDate now = LocalDate.now(); // localDate- 년 월 일
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String datePath = now.format(formatter);

        for (MultipartFile file : files) {
            // 방어코드. 혹시 모르는 것을 방지 하기 위해
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename(); // 내가 정해줬던 실제 파일 이름
            // 다운로드 할 때 사용할 이름 만들기
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
            Long fileSize = file.getSize();

            try {
                // 파일 저장 경로 설정
                Path directoryPath = Paths.get("/Users/hyunje/upload/" + datePath);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath); // 폴더가 없으면 생성
                }
                Path filePath = directoryPath.resolve(storedFileName);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setBoardId(boardId);
                fileDTO.setOriginalFileName(originalFileName);
                fileDTO.setStoredFileName(directoryPath + "/" + storedFileName);
                fileDTO.setFileSize(fileSize);

                fileMapper.insertFile(FileVO.toEntity(fileDTO)); // 파일 정보 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 게시글 삭제
    @Override
    public void deleteBoard(Long boardId) {
        boardMapper.deleteBoard(boardId);
    }



    // 최신순
    @Override
    public PagedResponse<BoardListDTO> selectAllByDateDESC(int page, int pageSize) {

        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalBoards = boardMapper.countBoard();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        int pageGroupSize = 5;


        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        List<BoardListDTO> boards = boardMapper.selectAll(startRow, endRow);

        return new PagedResponse<>(boards, page, totalPages, pageSize, totalBoards);
    }

    // 오래된 순
    @Override
    public PagedResponse<BoardListDTO> selectAllByDateASC(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalBoards = boardMapper.countBoard();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        int pageGroupSize = 5;


        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        List<BoardListDTO> boards = boardMapper.selectAllByDateASC(startRow, endRow);

        return new PagedResponse<>(boards, page, totalPages, pageSize, totalBoards);
    }

    // 조회순
    @Override
    public PagedResponse<BoardListDTO> selectAllByViews(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalBoards = boardMapper.countBoard();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        int pageGroupSize = 5;


        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        List<BoardListDTO> boards = boardMapper.selectAllByViews(startRow, endRow);

        return new PagedResponse<>(boards, page, totalPages, pageSize, totalBoards);
    }

    // 동적 쿼리
    @Override
    public PagedResponse<BoardListDTO> selectD(int page, int pageSize, String sort) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        int totalBoards = boardMapper.countBoard();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        int pageGroupSize = 5;


        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        List<BoardListDTO> boards = boardMapper.selectD(startRow, endRow, sort);

        return new PagedResponse<>(boards, page, totalPages, pageSize, totalBoards);
    }


}
