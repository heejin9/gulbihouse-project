package com.ssg.gulbihouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExpiringIngredientDto {
    private String name;
    private long daysToExpiration;
}
