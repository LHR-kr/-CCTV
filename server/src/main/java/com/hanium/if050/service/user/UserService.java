package com.hanium.if050.service.user;

import com.hanium.if050.domain.user.User;
import com.hanium.if050.domain.user.UserRepository;
import com.hanium.if050.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean register(UserRegisterRequestDto requestDto) {
        //TODO : 회원가입

        duplicateUser(requestDto.getId());

        userRepository.save(requestDto.toEntity());


        return true;
    }

    private void duplicateUser(String id) {
        userRepository.findById(id)
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });

    }
}
