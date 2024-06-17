package com.example.board.controller;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.dto.BoardListDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
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

}
