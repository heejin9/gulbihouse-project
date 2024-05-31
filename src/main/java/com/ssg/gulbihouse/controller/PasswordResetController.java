package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    // 비밀번호 찾기 및 인증번호 발송 페이지로 이동
    @GetMapping("/searchpassword")
    public String showSearchPasswordPage() {
        return "searchpassword";
    }

    // 비밀번호 찾기 요청 처리 (인증번호 발송)
    @PostMapping("/searchpassword")
    @ResponseBody
    public String handleSearchPassword(@RequestParam("email") String email, Model model) {
        boolean isSent = passwordResetService.sendPasswordResetCode(email);
        if (isSent) {
            return "인증번호가 이메일로 발송되었습니다.";
        } else {
            return "이메일 전송에 실패했습니다. 다시 시도해주세요.";
        }
    }

    // 인증번호 확인 처리 (Ajax 요청 처리)
    @PostMapping("/verifycode")
    @ResponseBody
    public ResponseEntity<String> handleVerifyCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        boolean isValid = passwordResetService.verifyCode(email, code);
        if (isValid) {
            return ResponseEntity.ok("인증번호가 확인되었습니다.");
        } else {
            return ResponseEntity.status(400).body("인증번호가 유효하지 않습니다. 다시 시도해주세요.");
        }
    }

    // 새로운 비밀번호 설정 페이지로 이동
    @GetMapping("/newpassword")
    public String showNewPasswordPage(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "newpassword";
    }

    // 새로운 비밀번호 설정 요청 처리
    @PostMapping("/newpassword")
    public String handleNewPassword(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword, Model model) {
        boolean isReset = passwordResetService.resetPassword(email, newPassword);
        if (isReset) {
            model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
            return "redirect:/login";
        } else {
            model.addAttribute("error", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
            return "newpassword";
        }
    }
}
