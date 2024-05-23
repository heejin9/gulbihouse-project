package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.SeasonalFood;
import com.ssg.gulbihouse.repository.SeasonalFoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonalFoodService {

    private final SeasonalFoodRepository seasonalFoodRepository;

    public SeasonalFoodService(SeasonalFoodRepository seasonalFoodRepository) {
        this.seasonalFoodRepository = seasonalFoodRepository;
    }

    // 월별 음식 목록을 가져오는 메서드
    public List<SeasonalFood> getFoodsByMonth(int month) {
        return seasonalFoodRepository.findByMonth(month);
    }

    // ID로 특정 음식을 가져오는 메서드
    public SeasonalFood getFoodById(Long id) {
        return seasonalFoodRepository.findById(id).orElse(null);
    }
}
