package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//식재료 정보를 관리하는 리포지토리 인터페이스
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // 특정 사용자 ID로 식재료를 조회하는 메서드
    List<Ingredient> findByUserId(Long userId);
}
