package com.hanium.if050.web.dto;

import com.hanium.if050.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;
import java.util.Date;

@Getter
public class UserRegisterRequestDto {

    private String id;
    private String password;
    private String name;
    private String phone;
    private Date birth;
    private String email;
    private String tokenFCM;


    @Builder
    public UserRegisterRequestDto(String id, String password,String name, String phone,Date birth, String email, String tokenFCM) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.email = email;
        this.tokenFCM = tokenFCM;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .name(name)
                .phone(phone)
                .birth(birth)
                .email(email)
                .tokenFCM(tokenFCM)
                .build();
    }
}
