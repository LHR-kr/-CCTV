package com.hanium.if050.web.controller;

import com.hanium.if050.domain.user.User;
import com.hanium.if050.domain.user.UserRepository;
import com.hanium.if050.web.dto.UserRegisterRequestDto;
import org.junit.After;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;





@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;


    @After
    public void tearDown() throws Exception{
        userRepository.deleteAll();
    }

    @Test
    public void 회원_등록() throws Exception {
        //given

        String id = "kwon";
        String password = "password";
        String name = "name";
        Date birth = new Date();
        String phone = "010101101";
        String email = "email";
        String tokenFCM = "tokenFCM";

        UserRegisterRequestDto requestDto = UserRegisterRequestDto.builder()
                .id(id)
                .password(password)
                .name(name)
                .birth(birth)
                .phone(phone)
                .email(email)
                .tokenFCM(tokenFCM)
                .build();



        String url = "http://localhost:" + port + "/user/register";

        // when

        ResponseEntity<Boolean> responseEntity=restTemplate.postForEntity(url, requestDto, Boolean.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.size()).isEqualTo(1);


    }


}