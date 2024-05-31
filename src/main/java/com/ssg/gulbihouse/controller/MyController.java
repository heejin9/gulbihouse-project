package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;

@RequiredArgsConstructor
@Controller
public class MyController {

    private final UserService userService;

    @GetMapping("/my")
    public String getMyPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);

        if (user.getProfileImage()!= null) {
            String base64 = Base64.getEncoder().encodeToString(user.getProfileImage());
            String imageDataUrl = "data:image/jpeg;base64," + base64; // 이미지 포맷에 맞게 변경

            model.addAttribute("profileImage", imageDataUrl);
        }
        return "my";
    }
}