package com.hanium.if050.domain.login;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Entity
public class Login {

    @Id
    private String id;

    private String password;

    private String name;

    private String phone;

    private Date birth;

    private String email;

    private String tokenFCM;

    @Builder
    public Login(String id, String password, String name, String phone, Date birth, String email, String tokenFCM) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.email = email;
        this.tokenFCM = tokenFCM;
    }

}
