package com.ssg.gulbihouse.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity // JPA 엔티티로 지정
@Getter // Lombok 어노테이션: getter 메소드를 자동 생성
@Setter // Lombok 어노테이션: setter 메소드를 자동 생성
@NoArgsConstructor // Lombok 어노테이션: 기본 생성자 자동 생성
public class ShoppingItem {
    @Id // 기본 키(primary key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID를 자동 생성하도록 설정
    private Long id;

    @Column(nullable = false) // 이 컬럼은 null 값을 허용하지 않음
    private String content; // 쇼핑 아이템의 내용

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정, 지연 로딩
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 설정, 이 컬럼은 null 값을 허용하지 않음
    @JsonBackReference // 순환 참조 방지를 위해 사용
    private User user; // 쇼핑 아이템을 소유한 사용자

    private boolean completed; // 쇼핑 아이템의 완료 여부
}
