package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventCreateRequest {
    private String title;
    private LocalDate start;
    private LocalDate end;
}
