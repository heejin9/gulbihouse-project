package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.Event;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import com.ssg.gulbihouse.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자 주입을 자동으로 생성해주는 롬복 어노테이션
@RequestMapping("/events") // 이 컨트롤러의 모든 요청은 /events로 시작함
public class EventController {
    private final EventService eventService; // 이벤트 서비스 빈을 주입받음

    @GetMapping
    public String viewEvents(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        // 사용자 ID로 이벤트 목록을 조회하고 모델에 추가하여 뷰로 전달
        List<Event> events = eventService.findAllByUserId(userDetails.getId());
        model.addAttribute("events", events);
        return "events"; // events.html이라는 뷰를 반환하도록 설정
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Event> getAllEvents(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 사용자 ID로 이벤트 목록을 조회하여 JSON 형태로 반환
        return eventService.findAllByUserId(userDetails.getId());
    }

    @PostMapping("/api")
    @ResponseBody
    public Event createEvent(@RequestBody Event event, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 새로운 이벤트를 생성하고 JSON 형태로 반환
        return eventService.save(event, userDetails.getId());
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Event updateEvent(@PathVariable("id") Long id, @RequestBody Event event, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 이벤트 ID를 설정하고 업데이트한 후 JSON 형태로 반환
        event.setId(id);
        return eventService.updateEvent(event, userDetails.getId());
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable("id") Long id) {
        // 이벤트를 삭제
        eventService.deleteById(id);
    }
}