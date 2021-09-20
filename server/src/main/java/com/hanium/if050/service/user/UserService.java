package com.hanium.if050.service.user;

import com.hanium.if050.domain.user.User;
import com.hanium.if050.domain.user.UserRepository;
import com.hanium.if050.web.dto.UserLoginRequestDto;
import com.hanium.if050.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean register(UserRegisterRequestDto requestDto) {
        //TODO : 회원가입

        if (duplicateUser(requestDto.getId())) {
            return false;
        }

        User user = requestDto.toEntity();

        userRepository.save(user);

        return true;
    }

    @Transactional
    public boolean login(UserLoginRequestDto requestDto) {
        Optional<User> findUser = userRepository.findById(requestDto.getUserId());

        if (findUser.isPresent()) {
            User user = findUser.get();

            String userPassword = user.getPassword();

            if (userPassword.equals(requestDto.getUserPassword())) {
                return true;
            }
        }

        return false;
    }

    private boolean duplicateUser(String id) {

        Optional<User> user = userRepository.findById(id);

        return user.isPresent();

    }
}
