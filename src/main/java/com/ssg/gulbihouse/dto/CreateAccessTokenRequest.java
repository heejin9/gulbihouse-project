package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

// 액세스 토큰 요청을 담고 있는 객체
@Getter
@Setter
public class CreateAccessTokenRequest {
    private String refreshToken; // 리프레시 토큰
}
