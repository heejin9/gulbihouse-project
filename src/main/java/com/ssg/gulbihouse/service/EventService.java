package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Event;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.repository.EventRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    // 사용자 ID로 이벤트 목록을 가져오는 메서드
    public List<Event> findAllByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }

    // 새로운 이벤트를 저장하는 메서드
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
