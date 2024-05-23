package com.ssg.gulbihouse.controller;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.dto.CreateAccessTokenRequest;
import com.ssg.gulbihouse.dto.CreateAccessTokenResponse;
import com.ssg.gulbihouse.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // REST API 컨트롤러를 명시
public class TokenApiController {
    private final TokenService tokenService; // 토큰 서비스 빈을 주입받음

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        // 새로운 액세스 토큰을 생성하고 HTTP 201 상태 코드와 함께 응답
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
}
