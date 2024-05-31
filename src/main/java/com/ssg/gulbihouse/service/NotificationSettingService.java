package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.NotificationSetting;
import com.ssg.gulbihouse.repository.NotificationSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationSettingService {

    private final NotificationSettingRepository notificationSettingRepository;

    public void updateNotificationSetting(Long userId, boolean enabled) {
        NotificationSetting setting = notificationSettingRepository.findByUserId(userId)
                .orElseGet(() -> new NotificationSetting(userId, enabled));
        setting.setEnabled(enabled);
        notificationSettingRepository.save(setting);
    }

    public NotificationSetting getNotificationSetting(Long userId) {
        return notificationSettingRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Notification setting not found for userId: " + userId));
    }

    public List<NotificationSetting> getAllSettings() {
        return notificationSettingRepository.findAll();
    }


}
