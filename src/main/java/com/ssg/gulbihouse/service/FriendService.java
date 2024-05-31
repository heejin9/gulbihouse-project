package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Friend;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;

    // 닉네임으로 친구 찾기
    public Optional<Friend> findByNicknameAndUser(String nickname, User user) {
        return friendRepository.findByNicknameAndUser(nickname, user);
    }

    // 친구 저장 메서드
    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }

    // 친구 삭제 메서드 추가
    public void delete(Friend friend) {
        friendRepository.delete(friend);
    }

    // 친구 중복 체크
    public boolean existsByNicknameAndUser(String nickname, User user) {
        return friendRepository.existsByNicknameAndUser(nickname, user);
    }
}
