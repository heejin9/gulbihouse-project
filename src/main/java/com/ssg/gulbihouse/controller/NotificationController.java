package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.domain.Notification;
import com.ssg.gulbihouse.service.NotificationService;
import com.ssg.gulbihouse.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public String viewNotifications(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userDetails.getId());
        model.addAttribute("notifications", notifications);
        return "notifications";
    }
}
