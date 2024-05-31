package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Memo;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddMemoRequest;
import com.ssg.gulbihouse.dto.UpdateMemoRequest;
import com.ssg.gulbihouse.repository.MemoRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 이 클래스가 Spring의 서비스 클래스임을 나타냄
@RequiredArgsConstructor // final로 선언된 필드들을 초기화하는 생성자를 자동으로 생성해주는 Lombok 어노테이션
public class MemoService {
    private final MemoRepository memoRepository; // Memo 엔티티를 위한 리포지토리
    private final UserRepository userRepository; // User 엔티티를 위한 리포지토리

    // 특정 사용자의 메모 목록을 조회하는 메소드
    public List<Memo> getUserMemos(Long userId) {
        // userId를 기준으로 메모 목록을 조회하여 반환
        return memoRepository.findByUserId(userId);
    }

    // 새로운 메모를 추가하는 메소드
    public Memo save(AddMemoRequest request, Long userId) {
        // userId를 이용해 사용자 정보를 조회, 사용자 정보가 없으면 예외 발생
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        // 새 메모 생성 및 속성 설정
        Memo memo = new Memo();
        memo.setContent(request.getContent());
        memo.setUser(user);
        // 메모를 저장하고 반환
        return memoRepository.save(memo);
    }

    // 기존 메모를 수정하는 메소드
    public Memo update(Long id, UpdateMemoRequest request) {
        // id를 이용해 기존 메모를 조회, 메모가 없으면 예외 발생
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid memo ID"));
        // 메모의 내용 업데이트
        memo.setContent(request.getContent());
        // 수정된 메모를 저장하고 반환
        return memoRepository.save(memo);
    }

    // 메모를 삭제하는 메소드
    public void delete(Long id) {
        // id를 이용해 메모를 삭제
        memoRepository.deleteById(id);
    }
}
