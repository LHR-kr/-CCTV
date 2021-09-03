package com.hanium.if050.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@NoArgsConstructor
@Document(collection="user")
public class User {

    @Id
    private String id;

    private String password;

    private String name;

    private String phone;

    private Date birth;

    private String email;

    private String tokenFCM;

    @Builder
    public User(String id, String password, String name, String phone, Date birth, String email, String tokenFCM) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.email = email;
        this.tokenFCM = tokenFCM;
    }

}
