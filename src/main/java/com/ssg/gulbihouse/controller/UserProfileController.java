package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.UpdateUserRequest;
import com.ssg.gulbihouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class UserProfileController {

    private final UserService userService; // 사용자 서비스 빈을 주입받음

    @GetMapping
    public String getUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        // 사용자 ID로 사용자 정보를 조회하고 모델에 추가하여 뷰로 전달
        User user = userService.findById(userDetails.getId());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/image")
    @ResponseBody
    public byte[] getProfileImage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 사용자 프로필 이미지를 바이너리 형태로 반환
        User user = userService.findById(userDetails.getId());
        return user.getProfileImage();
    }

    @PostMapping("/update")
    public String updateUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @ModelAttribute UpdateUserRequest request,
                                    @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
        // 사용자 프로필을 업데이트하고 프로필 페이지로 리다이렉트
        userService.updateUserProfile(userDetails.getId(), request, profileImage);
        return "redirect:/profile";
    }

    @PostMapping("/check-password")
    public ResponseEntity<?> checkPassword(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Map<String, String> request) {
        // 사용자 비밀번호를 확인하고 결과를 JSON 형태로 반환
        String password = request.get("password");
        boolean valid = userService.checkPassword(userDetails.getId(), password);
        return ResponseEntity.ok(Collections.singletonMap("valid", valid));
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<?> checkNickname(@RequestBody Map<String, String> request) {
        // 닉네임의 사용 가능 여부를 확인하고 결과를 JSON 형태로 반환
        String nickname = request.get("nickname");
        boolean available = userService.checkNicknameAvailability(nickname);
        return ResponseEntity.ok(Collections.singletonMap("available", available));
    }

    @PostMapping("/delete")
    public String deleteUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 사용자 프로필을 삭제하고 로그인 페이지로 리다이렉트
        userService.delete(userDetails.getId());
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

}