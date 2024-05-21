package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.domain.Ingredient;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddIngredientRequest;
import com.ssg.gulbihouse.dto.UpdateIngredientRequest;
import com.ssg.gulbihouse.repository.IngredientRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    // 새로운 식재료를 저장하는 메서드
    public Long save(AddIngredientRequest dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));

        Ingredient ingredient = Ingredient.builder()
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .expirationDate(dto.getExpirationDate())
                .status(dto.getStatus())
                .user(user)
                .build();

        return ingredientRepository.save(ingredient).getId();
    }

    // 사용자 ID로 식재료 목록을 조회하는 메서드
    public List<Ingredient> findByUserId(Long userId) {
        return ingredientRepository.findByUserId(userId);
    }

    // 특정 식재료를 삭제하는 메서드
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    // 특정 식재료를 업데이트하는 메서드
    public void update(Long id, UpdateIngredientRequest dto) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected ingredient"));

        ingredient.update(dto.getName(), dto.getQuantity(), dto.getExpirationDate(), dto.getStatus());
        ingredientRepository.save(ingredient);
    }
}
