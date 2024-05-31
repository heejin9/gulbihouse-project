package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationSettingRequest {
    private Long userId;
    private boolean enabled;
}
