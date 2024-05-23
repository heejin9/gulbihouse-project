package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// 사용자 정보 업데이트 요청을 담고 있는 객체
@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    private String nickname; // 새로운 닉네임
    private String password; // 현재 비밀번호
    private String newPassword; // 새로운 비밀번호
}
