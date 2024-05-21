package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//email로 사용자 정보 가져옴
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByNickname(String nickname);

}
