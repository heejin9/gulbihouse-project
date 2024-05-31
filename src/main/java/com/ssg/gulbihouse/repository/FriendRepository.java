package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.Friend;
import com.ssg.gulbihouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByNicknameAndUser(String nickname, User user);
    boolean existsByNicknameAndUser(String nickname, User user);

    Optional<Friend> findByNickname(String nickname);


}
