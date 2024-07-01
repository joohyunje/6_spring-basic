package com.example.board.controller;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardDetailDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.dto.FileDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.service.BoardService;
import com.example.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "7") int pageSize,
                       Model model) {

        int totalBoards = boardService.countBoard();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        List<BoardListDTO> boards = boardService.selectPaging(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        // html 로 넘겨야하는 값들은 뭐가 있을까
        // 1. 데이터  2. 지금 현재 페이지  3. 페이지 사이즈  4. 총 페이지 수
        // 5. 시작 페이지 수  6. 마지막 페이지 수
        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/list";
    }

    // 게시글 작성 폼으로 이동
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new BoardDTO());
        return "board/write";
    }

    @PostMapping("/write")
    public String write(BoardDTO board, @RequestParam("providerId") String providerId,
                        List<MultipartFile> files) {
        board.setProviderId(providerId);
        boardService.saveBoard(board, files);
        return "redirect:/board/list";
    }

    // 게시글 상세보기
    @GetMapping("/detail/{boardId}")
    public String detail(@PathVariable("boardId") Long boardId, Model model,
                         @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        BoardDetailDTO board = boardService.getBoardById(boardId, customOAuth2User);
        List<FileDTO> files = fileService.getFileListByBoardId(boardId);

        model.addAttribute("board", board);
        model.addAttribute("files", files);

        return "board/detail";
    }

    // 게시글 수정 폼으로 이동
    @GetMapping("/edit/{boardId}")
    public String edit(@PathVariable Long boardId, Model model) {
        model.addAttribute("board", boardService.goUpdateBoard(boardId));

        return "board/edit";
    }

    //게시글 수정
    @PostMapping("/edit")
    public String edit(BoardDTO board, List<MultipartFile> files) {
        boardService.updateBoard(board, files);

        return "redirect:/board/detail/" + board.getBoardId();
    }

    // 게시글 삭제
    @PostMapping ("/delete/{boardId}")
    public String delete(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board/list";
    }

    @GetMapping("/rest")
    public String rest() {
        return "board/restList";
    }

}
