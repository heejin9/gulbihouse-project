package com.ssg.gulbihouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 액세스 토큰 응답을 담고 있는 객체
@AllArgsConstructor
@Getter
public class CreateAccessTokenResponse {
    private String accessToken; // 액세스 토큰
}
