package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.SeasonalFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonalFoodRepository extends JpaRepository<SeasonalFood, Long> {
    List<SeasonalFood> findByMonth(int month);
}
