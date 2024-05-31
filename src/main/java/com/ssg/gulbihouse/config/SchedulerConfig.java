package com.ssg.gulbihouse.config;

import com.ssg.gulbihouse.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {

    private final IngredientService ingredientService;
    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    // 매일 오전 9시에 유통기한 알림을 체크하는 스케줄러
    @Scheduled(cron = "0 0 9 * * *")
    public void scheduleExpiryNotificationCheck() {
        logger.info("Running scheduled task for expiry notifications.");
//        ingredientService.checkAndSendExpiryNotifications();
    }
}
