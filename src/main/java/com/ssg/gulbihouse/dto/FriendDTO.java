package com.ssg.gulbihouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendDTO {
    private String nickname;
    private byte[] profileImage;
}
