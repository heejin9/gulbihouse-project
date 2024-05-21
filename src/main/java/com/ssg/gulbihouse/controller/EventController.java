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
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public String viewEvents(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<Event> events = eventService.findAllByUserId(userDetails.getId());
        model.addAttribute("events", events);
        return "events"; // events.html이라는 뷰를 반환하도록 설정
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Event> getAllEvents(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return eventService.findAllByUserId(userDetails.getId());
    }

    @PostMapping("/api")
    @ResponseBody
    public Event createEvent(@RequestBody Event event, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return eventService.save(event, userDetails.getId());
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Event updateEvent(@PathVariable("id") Long id, @RequestBody Event event, @AuthenticationPrincipal CustomUserDetails userDetails) {
        event.setId(id);
        return eventService.updateEvent(event, userDetails.getId());
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteById(id);
    }
}
