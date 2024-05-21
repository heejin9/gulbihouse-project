package com.ssg.gulbihouse.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//식재료 정보를 저장하는 엔티티 클래스
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // 식재료 이름

    @Column(name = "quantity", nullable = false)
    private int quantity; // 식재료 수량

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate; // 유통기한

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status; // 보관 상태 (냉장, 냉동, 실온)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자 정보 (외래키)

    @Builder
    public Ingredient(String name, int quantity, LocalDate expirationDate, Status status, User user) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.status = status;
        this.user = user;
    }

    // 보관 상태를 나타내는 열거형
    @Getter
    public enum Status {
        REFRIGERATED("냉장"),
        FROZEN("냉동"),
        ROOM_TEMPERATURE("실온");

        private final String desc;

        Status(String desc) {
            this.desc = desc;
        }
    }

    // 식재료 정보를 업데이트하는 메서드
    public void update(String name, int quantity, LocalDate expirationDate, Status status) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.status = status;
    }
}
