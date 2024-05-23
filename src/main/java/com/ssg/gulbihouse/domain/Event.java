package com.ssg.gulbihouse.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 이벤트 ID

    private String title; // 이벤트 제목

    private LocalDate start; // 시작 날짜

    private LocalDate end; // 종료 날짜

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 이벤트를 소유한 사용자
}
