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
@Controller // @RestController에서 @Controller로 변경
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public String viewIngredients(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<Ingredient> ingredients = ingredientService.findByUserId(userDetails.getId());
        model.addAttribute("ingredients", ingredients);
        return "ingredients"; // 뷰 이름 반환
    }

    @PostMapping("/add")
    public String addIngredient(AddIngredientRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        ingredientService.save(request, userDetails.getId());
        return "redirect:/ingredients";
    }

    @PostMapping("/update")
    public String updateIngredient(UpdateIngredientRequest request) {
        if (request.getId() == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        ingredientService.update(request.getId(), request);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete")
    public String deleteIngredient(@RequestParam("id") Long id) {
        ingredientService.delete(id);
        return "redirect:/ingredients";
    }
}
