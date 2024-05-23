package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.config.jwt.TokenProvider;
import com.ssg.gulbihouse.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor // 생성자 주입을 자동으로 생성해주는 롬복 어노테이션
@Service
public class TokenService {
    private final TokenProvider tokenProvider; // 토큰 제공자
    private final RefreshTokenService refreshTokenService; // 리프레시 토큰 서비스
    private final UserService userService; // 사용자 서비스

    // 새로운 액세스 토큰을 생성하는 메서드
    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        // 리프레시 토큰으로 사용자 ID를 찾음
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        // 사용자 ID를 찾은 후에 새로운 액세스 토큰을 생성
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
