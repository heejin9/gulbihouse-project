package com.ssg.gulbihouse.dto;

import lombok.Getter;
import lombok.Setter;

// 사용자 정보를 담고 있는 객체
@Getter
@Setter
public class AddUserRequest {
    private Long id;
    private String nickname;
    private String email;
    private String password;
//    private String profileimage;
}