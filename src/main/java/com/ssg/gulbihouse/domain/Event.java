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
    private Long id;

    private String title;

    private LocalDate start;

    private LocalDate end;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
