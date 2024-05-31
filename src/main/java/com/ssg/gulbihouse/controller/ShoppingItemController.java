package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.ShoppingItem;
import com.ssg.gulbihouse.dto.AddShoppingItemRequest;
import com.ssg.gulbihouse.dto.UpdateShoppingItemRequest;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.service.ShoppingItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // RESTful 웹 서비스의 컨트롤러임을 나타냄
@RequestMapping("/api/shopping-items") // 이 컨트롤러의 기본 요청 경로 설정
public class ShoppingItemController {

    private final ShoppingItemService shoppingItemService;

    // 현재 사용자의 쇼핑 아이템 목록을 조회하는 메소드
    @GetMapping
    public List<ShoppingItem> viewShoppingItems(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return shoppingItemService.getUserShoppingItems(userDetails.getId());
    }

    // 새로운 쇼핑 아이템을 추가하는 메소드
    @PostMapping
    public ShoppingItem addShoppingItem(@RequestBody AddShoppingItemRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return shoppingItemService.save(request, userDetails.getId());
    }

    // 기존 쇼핑 아이템을 수정하는 메소드
    @PutMapping("/{id}")
    public ShoppingItem updateShoppingItem(@PathVariable("id") Long id, @RequestBody UpdateShoppingItemRequest request) {
        return shoppingItemService.update(id, request);
    }

    // 쇼핑 아이템을 삭제하는 메소드
    @DeleteMapping("/{id}")
    public void deleteShoppingItem(@PathVariable("id") Long id) {
        shoppingItemService.delete(id);
    }
}
