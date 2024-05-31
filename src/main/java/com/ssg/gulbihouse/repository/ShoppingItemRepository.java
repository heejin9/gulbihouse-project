package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// ShoppingItem 엔티티에 대한 리포지토리 인터페이스 정의
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
    // 사용자 ID를 기반으로 쇼핑 아이템 목록 조회
    List<ShoppingItem> findByUserId(Long userId);
}
