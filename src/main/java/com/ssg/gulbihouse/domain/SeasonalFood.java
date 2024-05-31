package com.ssg.gulbihouse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SeasonalFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String englishName;  // 영어 이름 필드 추가
    private String category;
    private int month;
    private String imageUrl;
    private String storageMethod;
    private String nutritionInfo;
    private String recipe;

    // 기본 생성자
    public SeasonalFood() {}

    // 모든 필드를 포함한 생성자
    public SeasonalFood(String name, String englishName, String category, int month, String imageUrl, String storageMethod, String nutritionInfo) {
        this.name = name;
        this.englishName = englishName;  // 필드 초기화
        this.category = category;
        this.month = month;
        this.imageUrl = imageUrl;
        this.storageMethod = storageMethod;
        this.nutritionInfo = nutritionInfo;
    }

    // Getter 및 Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    public String getNutritionInfo() {
        return nutritionInfo;
    }

    public void setNutritionInfo(String nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
