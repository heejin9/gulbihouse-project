package com.ssg.gulbihouse.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id; // 친구 ID

    @Column(name = "nickname", nullable = false)
    private String nickname; // 친구 닉네임

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 친구가 속한 사용자

    @Builder
    public Friend(String nickname, User user) {
        this.nickname = nickname;
        this.user = user;
    }
}
