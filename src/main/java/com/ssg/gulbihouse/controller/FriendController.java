package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.Friend;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.FriendDTO;
import com.ssg.gulbihouse.service.FriendService;
import com.ssg.gulbihouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;

    // 친구 추가 엔드포인트
    @PostMapping("/friends")
    public ResponseEntity<?> addFriend(@RequestParam("userId") Long userId, @RequestParam("friendNickname") String friendNickname) {
        // 사용자 찾기
        User user = userService.findById(userId);

        // 친구 추가하려는 사용자를 찾기
        Optional<User> friendUserOpt = userService.findByNickname(friendNickname);
        if (friendUserOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User with the given nickname not found");
        }

        User friendUser = friendUserOpt.get();

        // 이미 친구인지 확인
        if (friendService.existsByNicknameAndUser(friendNickname, user)) {
            return ResponseEntity.badRequest().body("Friend already exists");
        }

        // 새로운 친구 객체 생성 및 사용자에 추가
        Friend newFriend = Friend.builder()
                .nickname(friendNickname)
                .user(user)
                .build();

        // 친구 저장
        friendService.save(newFriend);

        // 사용자 친구 목록에 추가
        user.getFriends().add(newFriend);
        userService.updateUser(user); // 사용자 업데이트

        // JSON 응답으로 친구 정보 반환
        return ResponseEntity.ok(Map.of("nickname", newFriend.getNickname(), "profileImage", Base64.getEncoder().encodeToString(friendUser.getProfileImage())));
    }

    // 사용자 ID로 친구 목록을 가져오는 엔드포인트
    @GetMapping("/friends")
    public List<Map<String, String>> getFriends(@RequestParam("userId") Long userId) {
        User user = userService.findById(userId);
        return user.getFriends().stream()
                .map(friend -> {
                    User friendUser = userService.findByNickname(friend.getNickname()).orElse(null);
                    if (friendUser != null) {
                        return Map.of(
                                "nickname", friend.getNickname(),
                                "profileImage", Base64.getEncoder().encodeToString(friendUser.getProfileImage())
                        );
                    }
                    return Map.of("nickname", friend.getNickname());
                })
                .collect(Collectors.toList());
    }

    // 친구 삭제 엔드포인트
    @DeleteMapping("/friends")
    public ResponseEntity<?> deleteFriend(@RequestParam("userId") Long userId, @RequestParam("friendNickname") String friendNickname) {
        // 사용자 찾기
        User user = userService.findById(userId);

        // 친구 찾기
        Friend friend = friendService.findByNicknameAndUser(friendNickname, user)
                .orElseThrow(() -> new IllegalArgumentException("Friend not found"));

        // 사용자 친구 목록에서 친구 제거
        user.getFriends().remove(friend);

        // 친구 삭제
        friendService.delete(friend);

        // 사용자 업데이트
        userService.updateUser(user);

        return ResponseEntity.ok("Friend deleted successfully");
    }
}
