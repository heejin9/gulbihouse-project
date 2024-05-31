package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.ShoppingItem;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddShoppingItemRequest;
import com.ssg.gulbihouse.dto.UpdateShoppingItemRequest;
import com.ssg.gulbihouse.repository.ShoppingItemRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 이 클래스가 Spring의 서비스 클래스임을 나타냄
@RequiredArgsConstructor // final로 선언된 필드들을 초기화하는 생성자를 자동으로 생성해주는 Lombok 어노테이션
public class ShoppingItemService {
    private final ShoppingItemRepository shoppingItemRepository;
    private final UserRepository userRepository;

    // 특정 사용자의 쇼핑 아이템 목록을 조회하는 메소드
    public List<ShoppingItem> getUserShoppingItems(Long userId) {
        // userId를 기준으로 쇼핑 아이템 목록을 조회하여 반환
        return shoppingItemRepository.findByUserId(userId);
    }

    // 새로운 쇼핑 아이템을 추가하는 메소드
    public ShoppingItem save(AddShoppingItemRequest request, Long userId) {
        // userId를 이용해 사용자 정보를 조회, 사용자 정보가 없으면 예외 발생
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        // 새 쇼핑 아이템 생성 및 속성 설정
        ShoppingItem item = new ShoppingItem();
        item.setContent(request.getContent());
        item.setCompleted(request.isCompleted());
        item.setUser(user);
        // 쇼핑 아이템을 저장하고 반환
        return shoppingItemRepository.save(item);
    }

    // 기존 쇼핑 아이템을 수정하는 메소드
    public ShoppingItem update(Long id, UpdateShoppingItemRequest request) {
        // id를 이용해 기존 쇼핑 아이템을 조회, 아이템이 없으면 예외 발생
        ShoppingItem item = shoppingItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        // 아이템의 속성 업데이트
        item.setContent(request.getContent());
        item.setCompleted(request.isCompleted());
        // 수정된 아이템을 저장하고 반환
        return shoppingItemRepository.save(item);
    }

    // 쇼핑 아이템을 삭제하는 메소드
    public void delete(Long id) {
        // id를 이용해 쇼핑 아이템을 삭제
        shoppingItemRepository.deleteById(id);
    }
}
