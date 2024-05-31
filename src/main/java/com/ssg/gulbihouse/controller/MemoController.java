package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.Memo;
import com.ssg.gulbihouse.dto.AddMemoRequest;
import com.ssg.gulbihouse.dto.UpdateMemoRequest;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 필드들을 초기화하는 생성자를 자동으로 생성해주는 Lombok 어노테이션
@RestController // RESTful 웹 서비스의 컨트롤러임을 나타냄
@RequestMapping("/api/memos") // 이 컨트롤러의 기본 요청 경로 설정
public class MemoController {

    private final MemoService memoService;

    // 현재 사용자의 메모 목록을 조회하는 메소드
    @GetMapping
    public List<Memo> viewMemos(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // userDetails 객체에서 사용자 ID를 가져와서 해당 사용자의 메모 목록을 반환
        return memoService.getUserMemos(userDetails.getId());
    }

    // 새로운 메모를 추가하는 메소드
    @PostMapping
    public Memo addMemo(@RequestBody AddMemoRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 사용자 ID와 요청 데이터를 이용해 새로운 메모를 저장하고 반환
        return memoService.save(request, userDetails.getId());
    }

    // 기존 메모를 수정하는 메소드
    @PutMapping("/{id}")
    public Memo updateMemo(@PathVariable("id") Long id, @RequestBody UpdateMemoRequest request) {
        // 메모 ID와 요청 데이터를 이용해 메모를 수정하고 반환
        return memoService.update(id, request);
    }

    // 메모를 삭제하는 메소드
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable("id") Long id) {
        // 메모 ID를 이용해 메모를 삭제
        memoService.delete(id);
    }
}
