package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Event 엔티티를 관리하는 리포지토리 인터페이스
public interface EventRepository extends JpaRepository<Event, Long> {
    // 특정 사용자 ID로 이벤트 목록을 찾는 메서드
    List<Event> findByUserId(Long userId);
}
