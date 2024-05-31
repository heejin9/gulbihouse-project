package com.ssg.gulbihouse.service;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.domain.Ingredient;
import com.ssg.gulbihouse.domain.NotificationSetting;
import com.ssg.gulbihouse.domain.User;
import com.ssg.gulbihouse.dto.AddIngredientRequest;
import com.ssg.gulbihouse.dto.UpdateIngredientRequest;
import com.ssg.gulbihouse.repository.IngredientRepository;
import com.ssg.gulbihouse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final NotificationSettingService notificationSettingService;
    private final NotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(IngredientService.class);

    // 새로운 식재료를 저장하는 메서드
    public Long save(AddIngredientRequest dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));

        Ingredient ingredient = Ingredient.builder()
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .expirationDate(dto.getExpirationDate())
                .status(dto.getStatus())
                .user(user)
                .build();

        return ingredientRepository.save(ingredient).getId();
    }

    // 사용자 ID로 식재료 목록을 조회하는 메서드
    public List<Ingredient> findByUserId(Long userId) {
        return ingredientRepository.findByUserId(userId);
    }

    // 특정 식재료를 삭제하는 메서드
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    // 특정 식재료를 업데이트하는 메서드
    public void update(Long id, UpdateIngredientRequest dto) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected ingredient"));

        ingredient.update(dto.getName(), dto.getQuantity(), dto.getExpirationDate(), dto.getStatus());
        ingredientRepository.save(ingredient);
    }

    // 유통기한을 체크하고 알림을 생성하는 메서드
//    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    public void checkAndSendExpiryNotifications() {
//        List<Ingredient> ingredients = ingredientRepository.findAll();
//        logger.info("Checking expiry notifications for " + ingredients.size() + " ingredients.");
//
//        for (Ingredient ingredient : ingredients) {
//            long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), ingredient.getExpirationDate());
//            logger.info("Ingredient: " + ingredient.getName() + ", Days until expiry: " + daysUntilExpiry);
//
//            if (daysUntilExpiry == 7 || daysUntilExpiry == 3 || daysUntilExpiry == 0) {
//                NotificationSetting setting = notificationSettingService.getNotificationSetting(ingredient.getUser().getId());
//
//                if (setting != null && setting.isEnabled()) {
//                    String message = "Your ingredient " + ingredient.getName() + " will expire in " + daysUntilExpiry + " days.";
//                    notificationService.createNotification(message, ingredient.getId());
//                    logger.info("Notification for ingredient: " + ingredient.getName() + " sent to userId: " + ingredient.getUser().getId());
//                } else {
//                    logger.info("Notification setting not found or not enabled for userId: " + ingredient.getUser().getId());
//                }
//            } else {
//                logger.info("Ingredient: " + ingredient.getName() + " does not meet notification criteria.");
//            }
//        }
//    }

//    public void checkAndSendExpiryNotifications() {
//        List<Ingredient> ingredients = ingredientRepository.findAll();
//        LocalDate today = LocalDate.now();
//
//        for (Ingredient ingredient : ingredients) {
//            if (ingredient.getExpirationDate().isBefore(today.plusDays(3))) {
//                String message = "Ingredient " + ingredient.getName() + " is expiring soon!";
//                notificationService.createNotification(message, ingredient.getId());
//            }
//        }
//    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // 유통기한 7일 전까지의 재료를 가져오는 메서드
    public List<Ingredient> getExpiringIngredients(Long userId) {
        List<Ingredient> ingredients = ingredientRepository.findByUserId(userId);
        return ingredients.stream()
                .filter(ingredient -> {
                    long daysToExpiration = ChronoUnit.DAYS.between(LocalDate.now(), ingredient.getExpirationDate());
                    return daysToExpiration <= 7 && daysToExpiration >= 0;
                })
                .sorted((a, b) -> {
                    long daysToExpirationA = ChronoUnit.DAYS.between(LocalDate.now(), a.getExpirationDate());
                    long daysToExpirationB = ChronoUnit.DAYS.between(LocalDate.now(), b.getExpirationDate());
                    return Long.compare(daysToExpirationA, daysToExpirationB);
                })
                .collect(Collectors.toList());
    }

}