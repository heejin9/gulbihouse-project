package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Memo 엔티티에 대한 리포지토리 인터페이스 정의
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // 사용자 ID를 기반으로 메모 목록 조회
    List<Memo> findByUserId(Long userId);
}
