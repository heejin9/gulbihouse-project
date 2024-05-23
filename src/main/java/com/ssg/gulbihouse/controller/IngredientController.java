package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.dto.AddIngredientRequest;
import com.ssg.gulbihouse.dto.UpdateIngredientRequest;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.service.IngredientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService; // 재료 서비스 빈을 주입받음

    @GetMapping
    public String viewIngredients(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        // 사용자 ID로 재료 목록을 조회하고 모델에 추가하여 뷰로 전달
        List<Ingredient> ingredients = ingredientService.findByUserId(userDetails.getId());
        model.addAttribute("ingredients", ingredients);
        return "ingredients"; // 뷰 이름 반환
    }

    @PostMapping("/add")
    public String addIngredient(AddIngredientRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 새로운 재료를 추가하고 재료 목록 페이지로 리다이렉트
        ingredientService.save(request, userDetails.getId());
        return "redirect:/ingredients";
    }

    @PostMapping("/update")
    public String updateIngredient(UpdateIngredientRequest request) {
        // 재료 ID가 없으면 예외 발생, 있으면 재료를 업데이트하고 재료 목록 페이지로 리다이렉트
        if (request.getId() == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        ingredientService.update(request.getId(), request);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete")
    public String deleteIngredient(@RequestParam("id") Long id) {
        // 재료를 삭제하고 재료 목록 페이지로 리다이렉트
        ingredientService.delete(id);
        return "redirect:/ingredients";
    }
}
