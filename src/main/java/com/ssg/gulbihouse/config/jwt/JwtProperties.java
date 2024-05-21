package com.ssg.gulbihouse.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 해당 값들을 변수로 접근하는데 사용할 클래스
// application.properties의 jwt.issuer 값과 jwt.secret_key 값이 매핑됨
@Setter
@Getter
@Component
// 자바 클래스에 프로피티 값을 가져와서 사용
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;
}