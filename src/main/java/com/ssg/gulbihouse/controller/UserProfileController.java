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

    private final UserService userService;

    @GetMapping
    public String getUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        User user = userService.findById(userDetails.getId());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/update")
    public String updateUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @ModelAttribute UpdateUserRequest request,
                                    @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
        userService.updateUserProfile(userDetails.getId(), request, profileImage);
        return "redirect:/profile";
    }

    @PostMapping("/check-password")
    public ResponseEntity<?> checkPassword(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Map<String, String> request) {
        String password = request.get("password");
        boolean valid = userService.checkPassword(userDetails.getId(), password);
        return ResponseEntity.ok(Collections.singletonMap("valid", valid));
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<?> checkNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        boolean available = userService.checkNicknameAvailability(nickname);
        return ResponseEntity.ok(Collections.singletonMap("available", available));
    }

    @PostMapping("/delete")
    public String deleteUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.delete(userDetails.getId());
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }
}
