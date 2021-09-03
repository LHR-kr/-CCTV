package com.hanium.if050.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String userId;
    private String userPassword;

    @Builder
    public UserLoginRequestDto(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

}
