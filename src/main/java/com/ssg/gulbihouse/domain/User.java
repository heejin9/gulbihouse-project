package com.ssg.gulbihouse.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Blob;
import java.util.Collection;
import java.util.List;
import java.util.Set;

// 사용자 정보를 저장하는 엔티티 클래스
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id; // 사용자 ID

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname; // 사용자 닉네임

    @Column(name = "email", nullable = false, unique = true)
    private String email; // 사용자 이메일

    @Column(name = "password", nullable = false)
    private String password; // 사용자 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = true)
    private Role role; // 사용자 역할

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] profileImage; // 프로필 이미지

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Event> events; // 사용자의 이벤트 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ingredient> ingredients; // 사용자의 식재료 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friend> friends; // 사용자의 친구 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Memo> memos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ShoppingItem> shoppingItems;

    // 사용자 생성자
    @Builder
    public User(Long id, String nickname, String email, String password, Role role) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // 권한을 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 사용자 이름을 반환하는 메서드 (이메일을 사용자 이름으로 사용)
    @Override
    public String getUsername() {
        return email;
    }

    // 비밀번호를 반환하는 메서드
    @Override
    public String getPassword() {
        return password;
    }

    // 계정이 만료되지 않았는지 확인하는 메서드
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠기지 않았는지 확인하는 메서드
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명이 만료되지 않았는지 확인하는 메서드
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되었는지 확인하는 메서드
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Role 열거형 추가
    public enum Role {
        USER, ADMIN
    }

    // 추가된 setter 메서드
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
