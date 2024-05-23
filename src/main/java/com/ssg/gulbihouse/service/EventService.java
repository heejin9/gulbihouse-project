package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Event;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.repository.EventRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 주입을 자동으로 생성해주는 롬복 어노테이션
public class EventService {
    private final EventRepository eventRepository; // 이벤트 리포지토리
    private final UserRepository userRepository; // 사용자 리포지토리

    // 특정 사용자 ID로 이벤트 목록을 조회하는 메서드
    public List<Event> findAllByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }

    // 이벤트를 저장하는 메서드
    public Event save(Event event, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        event.setUser(user);
        return eventRepository.save(event);
    }

    // 이벤트를 업데이트하는 메서드
    public Event updateEvent(Event event, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        event.setUser(user);
        return eventRepository.save(event);
    }

    // 이벤트를 삭제하는 메서드
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
