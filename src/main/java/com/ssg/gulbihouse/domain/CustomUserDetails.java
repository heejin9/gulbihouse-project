package com.ssg.gulbihouse.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

//사용자 ID를 포함하는 커스텀 UserDetails 클래스
public class CustomUserDetails extends User {

    private final Long id;

    // CustomUserDetails 생성자
    public CustomUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities); // 부모 클래스의 생성자 호출
        this.id = id; // 사용자 ID 저장
    }

    // 사용자 ID 반환 메서드
    public Long getId() {
        return id;
    }


}