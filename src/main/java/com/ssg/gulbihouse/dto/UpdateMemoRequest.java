package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // Lombok 어노테이션: getter 메소드를 자동 생성
@Setter // Lombok 어노테이션: setter 메소드를 자동 생성
public class UpdateMemoRequest {
    private Long id; // 메모의 고유 ID
    private String content; // 메모의 내용
}
