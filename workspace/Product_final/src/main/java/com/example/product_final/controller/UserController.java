package com.example.product_final.controller;

import com.example.product_final.domain.dto.UserDTO;
import com.example.product_final.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/login/signup";
    }

    @PostMapping("/signup")
    public String signup(UserDTO dto) {
//        log.info("html 넘어온 데이터!!!!" + dto.toString());

        userService.save(dto);

        return "/login/login";
    }

    // 로그아웃 구현.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 우리가 직접 세션을 비워주고, 쿠키를 삭제해주는 것을 하지 않고
        // 스프링 시큐리티에게 정보들만 넘겨줘서 하도록 시키는 코드.

        // request 와 response 를 받아와야하는 이유는 쿠키를 삭제하고 세션을 비우는 메소드가
        // 해당 객체를 내에 있기 때문.
        new SecurityContextLogoutHandler().logout(request, response,
                // 현재 로그인 되어있는 객체를 전달해줌으로써 해당 객체와 관련된 정보들을 삭제해준다.
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/product/list";
    }

}
