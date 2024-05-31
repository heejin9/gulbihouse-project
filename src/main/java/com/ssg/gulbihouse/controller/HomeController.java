package com.ssg.gulbihouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // 필요한 데이터를 모델에 추가
        return "/home"; // home.html을 반환
    }
}
