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

    public List<Event> findAllByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }

    public Event save(Event event, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        event.setUser(user);
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        event.setUser(user);
        return eventRepository.save(event);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
