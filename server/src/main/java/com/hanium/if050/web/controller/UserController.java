package com.hanium.if050.web.controller;

import com.hanium.if050.service.user.UserService;
import com.hanium.if050.web.dto.UserLoginRequestDto;
import com.hanium.if050.web.dto.UserRegisterRequestDto;
import lombok.Data;
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
    public loginResponse login(@RequestBody UserLoginRequestDto requestDto) {

        boolean status = userService.login(requestDto);

        return new loginResponse(status);
    }

    @PostMapping("/user/register")
    public loginResponse register(@RequestBody UserRegisterRequestDto requestDto) {

        boolean status= userService.register(requestDto);
        return new loginResponse(status);
    }

    @GetMapping("/user/withdrawal")
    public void withdrawal() {
        //TODO : 회원탈퇴
    }

    @Data
    static class loginResponse{
        private boolean status;

        public loginResponse(boolean status) {
            this.status = status;
        }
    }


}
