package com.ssg.gulbihouse.dto;

import com.ssg.gulbihouse.domain.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//식재료 추가 요청을 위한 데이터 전송 객체
@Getter
@Setter
public class AddIngredientRequest {
    private String name; // 식재료 이름
    private int quantity; // 식재료 수량
    private LocalDate expirationDate; // 유통기한
    private Ingredient.Status status; // 보관 상태 (냉장, 냉동, 실온)
}
