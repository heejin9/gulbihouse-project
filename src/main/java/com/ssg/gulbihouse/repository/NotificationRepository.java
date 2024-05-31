package com.ssg.gulbihouse.repository;

import com.ssg.gulbihouse.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIngredientUserId(Long userId);
}
