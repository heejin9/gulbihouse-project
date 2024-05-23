package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// User 엔티티를 관리하는 리포지토리 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 정보를 찾는 메서드
    Optional<User> findByEmail(String email);

    // 닉네임이 존재하는지 확인하는 메서드
    boolean existsByNickname(String nickname);
}
