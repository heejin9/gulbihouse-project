package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    private String nickname;
    private String password; // Current password
    private String newPassword; // New password
}
