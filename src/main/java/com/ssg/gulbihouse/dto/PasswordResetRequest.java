package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

// 비밀번호 재설정 요청을 담는 DTO 클래스
@Getter
@Setter
public class PasswordResetRequest {
    private String email;
    private String code;
    private String newPassword;
}
