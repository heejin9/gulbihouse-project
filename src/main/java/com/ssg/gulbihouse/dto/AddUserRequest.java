package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

// 사용자 정보를 담고 있는 객체
@Getter
@Setter
public class AddUserRequest {
    private Long id; // 사용자 ID
    private String nickname; // 사용자 닉네임
    private String email; // 사용자 이메일
    private String password; // 사용자 비밀번호
    // private String profileimage; // 프로필 이미지
}
