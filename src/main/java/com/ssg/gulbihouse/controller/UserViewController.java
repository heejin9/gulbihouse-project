package com.ssg.gulbihouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 회원가입, 로그인 화면으로 연결하고 사용자가 실제로 볼 수 있는 화면
@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}