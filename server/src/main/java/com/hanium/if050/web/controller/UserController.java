package com.hanium.if050.web.controller;

import com.hanium.if050.service.user.UserService;
import com.hanium.if050.web.dto.UserLoginRequestDto;
import com.hanium.if050.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/login")
    public boolean login(@RequestBody UserLoginRequestDto requestDto) {


        return userService.login(requestDto);
    }

    @PostMapping("/user/register")
    public boolean register(@RequestBody UserRegisterRequestDto requestDto) {

        return userService.register(requestDto);
    }

    @GetMapping("/user/withdrawal")
    public void withdrawal() {
        //TODO : 회원탈퇴
    }
}
