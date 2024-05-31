package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.dto.ExpiringIngredientDto;
import com.ssg.gulbihouse.service.IngredientService;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExpiringIngredientController {

    private final IngredientService ingredientService;

    // 유통기한이 임박한 재료를 가져오는 엔드포인트
    @GetMapping("/expiring-ingredients")
    public List<ExpiringIngredientDto> getExpiringIngredients(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ingredientService.getExpiringIngredients(userDetails.getId()).stream()
                .map(ingredient -> new ExpiringIngredientDto(
                        ingredient.getName(),
                        ChronoUnit.DAYS.between(LocalDate.now(), ingredient.getExpirationDate())))
                .collect(Collectors.toList());
    }
}
