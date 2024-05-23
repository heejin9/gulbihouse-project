package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.SeasonalFood;
import com.ssg.gulbihouse.service.SeasonalFoodService;
import com.ssg.gulbihouse.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class FoodController {

    private final SeasonalFoodService seasonalFoodService;
    private final RecipeService recipeService;

    public FoodController(SeasonalFoodService seasonalFoodService, RecipeService recipeService) {
        this.seasonalFoodService = seasonalFoodService;
        this.recipeService = recipeService;
    }

    // 월별 음식 목록을 가져오는 메서드
    @GetMapping("/foods")
    public String getFoodsByMonth(@RequestParam(name = "month", required = false, defaultValue = "1") int month, Model model) {
        List<SeasonalFood> foods = seasonalFoodService.getFoodsByMonth(month);
        List<String> categories = Arrays.asList("과일", "채소", "해산물");
        model.addAttribute("foods", foods);
        model.addAttribute("month", month);
        model.addAttribute("categories", categories);
        return "foods";
    }

    // 특정 음식의 상세 정보를 가져오는 메서드
    @GetMapping("/foods/{id}")
    public String getFoodDetails(@PathVariable Long id, Model model) {
        SeasonalFood food = seasonalFoodService.getFoodById(id);
        String recipe = recipeService.getRecipe(food.getName());
        food.setRecipe(recipe);
        model.addAttribute("food", food);
        return "food-details";
    }
}
