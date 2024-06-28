package com.example.board.controller;

import com.example.board.domain.dto.BoardListDTO;
import com.example.board.domain.util.PagedResponse;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<PagedResponse<BoardListDTO>> getBoardList(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @RequestParam(defaultValue = "") String sort) {

//        PagedResponse<BoardListDTO> sortedBoards = switch (sort){
//            case "oldest" -> boardService.selectAllByDateASC(page, size);
//            case "views" -> boardService.selectAllByViews(page, size);
//            default -> boardService.selectAllByDateDESC(page, size);
//        };

        return ResponseEntity.ok(boardService.selectD(page, size, sort));
    }

}
