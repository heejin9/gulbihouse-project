package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddUserRequest;
import com.ssg.gulbihouse.dto.UpdateUserRequest;
import com.ssg.gulbihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public boolean checkPassword(Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    public boolean checkNicknameAvailability(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

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

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
