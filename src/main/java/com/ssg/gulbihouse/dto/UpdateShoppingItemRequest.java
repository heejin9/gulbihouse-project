package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // Lombok 어노테이션: getter 메소드를 자동 생성
@Setter // Lombok 어노테이션: setter 메소드를 자동 생성
public class UpdateShoppingItemRequest {
    private Long id; // 쇼핑 항목의 고유 ID
    private String content; // 쇼핑 항목의 내용
    private boolean completed; // 쇼핑 항목의 완료 여부를 나타내는 플래그
}
