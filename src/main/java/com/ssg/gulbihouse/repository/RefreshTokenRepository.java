package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// RefreshToken 엔티티를 관리하는 리포지토리 인터페이스
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // 특정 사용자 ID로 리프레시 토큰을 찾는 메서드
    Optional<RefreshToken> findByUserId(Long userId);

    // 리프레시 토큰 값으로 리프레시 토큰을 찾는 메서드
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
