package com.example.board.controller;

import com.example.board.domain.dto.*;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.vo.UsersVO;
import com.example.board.mapper.UsersMapper;
import com.example.board.service.BoardService;
import com.example.board.service.FileService;
import jakarta.servlet.http.HttpSession;
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
    private final UsersMapper usersMapper;

    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Model model) {

        int totalBoards = boardService.getBoardListCount();
        int totalPages = (int) Math.ceil((double)totalBoards/pageSize);

        List<BoardListDTO> boards = boardService.getBoardList(pageNo, pageSize);

        int pageGroupSize = 5;
        int startPage = ((pageNo - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

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

    // 게시글 작성 처리
    @PostMapping("/write")
    public String write(BoardDTO board, @RequestParam("providerId") String providerId,
                        @RequestParam("boardfiles") List<MultipartFile> files) {
        System.out.println(providerId);

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

    // 게시글 수정
    @PostMapping("/edit")
    public String edit(BoardDTO board, @RequestParam("boardfiles") List<MultipartFile> files) {
        boardService.updateBoard(board, files);

        return "redirect:/board/detail/" + board.getBoardId();
    }

    @PostMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board/list";
    }

    @GetMapping("/rest")
    public String rest() {
        return "board/restList";
    }

    @GetMapping("/join")
    public String join() {
        return "board/joinForm";
    }

    @PostMapping("/join")
    public String join(@RequestParam String phoneNumber,
                       @RequestParam String address,
                       @AuthenticationPrincipal CustomOAuth2User customOAuth2User) {

        UsersDTO usersDTO = usersMapper.findByProviderId(customOAuth2User.getProviderId());

        usersDTO.setRole("basic");
        usersDTO.setPhoneNumber(phoneNumber);
        usersDTO.setAddress(address);

        usersMapper.insertNewUser(UsersVO.toEntity(usersDTO));

        return "redirect:/board/list";
    }

    @GetMapping("login")
    public String goForm(HttpSession session){

        session.invalidate();

        return "board/loginForm";
    }





}