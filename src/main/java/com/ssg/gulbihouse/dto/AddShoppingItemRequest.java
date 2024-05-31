package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // Lombok 어노테이션: getter 메소드를 자동 생성
@Setter // Lombok 어노테이션: setter 메소드를 자동 생성
public class AddShoppingItemRequest {
    private String content; // 쇼핑 아이템의 내용
    private boolean completed; // 쇼핑 아이템의 완료 여부
}
