package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // 생성자 주입을 자동으로 생성해주는 롬복 어노테이션
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository; // 사용자 리포지토리

    // 사용자 이메일로 사용자 정보를 조회하고 CustomUserDetails를 반환
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));

        // 권한 목록을 설정
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        // CustomUserDetails 객체를 생성하여 반환
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), authorities);
    }
}
