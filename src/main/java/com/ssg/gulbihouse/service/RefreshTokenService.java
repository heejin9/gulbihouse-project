package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.domain.RefreshToken;
import com.ssg.gulbihouse.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // 생성자 주입을 자동으로 생성해주는 롬복 어노테이션
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository; // 리프레시 토큰 리포지토리

    // 리프레시 토큰 값으로 리프레시 토큰 객체를 찾는 메서드
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
