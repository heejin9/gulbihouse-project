package com.ssg.gulbihouse.controller;

import com.ssg.gulbihouse.dto.NotificationSettingRequest;
import com.ssg.gulbihouse.service.NotificationSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings/notifications")
@RequiredArgsConstructor
public class NotificationSettingController {

    private final NotificationSettingService notificationSettingService;

    @PostMapping("/expiry")
    public void updateExpiryNotificationSetting(@RequestBody NotificationSettingRequest request) {
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        // 알림 설정 업데이트
        notificationSettingService.updateNotificationSetting(request.getUserId(), request.isEnabled());
    }
}
