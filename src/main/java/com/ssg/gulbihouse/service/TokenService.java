package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.config.jwt.TokenProvider;
import com.ssg.gulbihouse.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

// 전달받은 리프레시 토큰으로 토큰 유효성 검사를 진행하고,
// 유효한 토큰일 때 리프레시 토큰으로 사용자 ID응 찾는 메서드
@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        // 사용자 ID를 찾은 후에 토큰 제공자의 메서드를 호출해서 새로운 엑세스 토큰을 생성하는 메서드
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}