package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddUserRequest;
import com.ssg.gulbihouse.dto.UpdateUserRequest;
import com.ssg.gulbihouse.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor // 생성자 주입을 자동으로 생성해주는 롬복 어노테이션
@Service
public class UserService {

    private final UserRepository userRepository; // 사용자 리포지토리
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 인코더

    // 새로운 사용자를 저장하는 메서드
    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // 사용자 ID로 사용자 정보를 찾는 메서드
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    // 사용자의 비밀번호를 확인하는 메서드
    public boolean checkPassword(Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    // 닉네임의 사용 가능 여부를 확인하는 메서드
    public boolean checkNicknameAvailability(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    // 사용자 프로필을 업데이트하는 메서드
    public void updateUserProfile(Long userId, UpdateUserRequest dto, MultipartFile profileImage) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));

        if (dto.getNickname() != null && !dto.getNickname().isEmpty()) {
            user.setNickname(dto.getNickname());
        }

        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(dto.getNewPassword()));
        }

        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                user.setProfileImage(profileImage.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to store profile image", e);
            }
        }

        userRepository.save(user);
    }

    // 사용자를 삭제하는 메서드
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user email: " + email));
    }
}
