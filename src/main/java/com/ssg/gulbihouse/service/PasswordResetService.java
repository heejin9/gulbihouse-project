package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    // 임시 저장소로 인증번호를 저장
    private Map<String, String> verificationCodes = new HashMap<>();

    // 이메일로 인증번호를 전송하는 메서드
    public boolean sendPasswordResetCode(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return false;
        }

        String code = generateVerificationCode();
        emailService.sendEmail(email, "비밀번호 재설정 인증번호", "인증번호: " + code);
        verificationCodes.put(email, code);
        return true;
    }

    // 인증번호 생성 메서드
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // 인증번호 검증 메서드
    public boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }

    // 비밀번호 재설정 메서드
    public boolean resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 이메일"));
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
        verificationCodes.remove(email);
        return true;
    }
}
