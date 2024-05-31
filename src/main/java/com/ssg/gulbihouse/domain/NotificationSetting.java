package com.ssg.gulbihouse.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    public NotificationSetting(Long userId, boolean enabled) {
        this.userId = userId;
        this.enabled = enabled;
    }
}
