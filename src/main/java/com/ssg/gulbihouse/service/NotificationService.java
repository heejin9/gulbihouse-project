package com.ssg.gulbihouse.service;

import com.ssg.gulbihouse.domain.Ingredient;
import com.ssg.gulbihouse.domain.Notification;
import com.ssg.gulbihouse.repository.IngredientRepository;
import com.ssg.gulbihouse.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final IngredientRepository ingredientRepository;
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    // 새로운 알림을 생성하는 메서드
    public void createNotification(String message, Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID: " + ingredientId));

        Notification notification = Notification.builder()
                .message(message)
                .date(LocalDateTime.now())
                .ingredient(ingredient)
                .build();
        notificationRepository.save(notification);
        logger.info("Notification created: " + message + " for ingredient: " + ingredient.getName());
    }

    // 특정 사용자의 알림을 조회하는 메서드
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByIngredientUserId(userId);
    }

    // 모든 알림을 조회하는 메서드
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
