package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.domain.RefreshToken;
import com.ssg.gulbihouse.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

// 전달받은 리프레시 토큰으로 리프레시 토큰 객체를 검색해서 전달하는 메서드
@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}