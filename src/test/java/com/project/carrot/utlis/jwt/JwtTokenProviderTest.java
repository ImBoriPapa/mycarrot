package com.project.carrot.utlis.jwt;

import com.project.carrot.domain.Login.service.LoginService;
import com.project.carrot.domain.Login.service.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Enumeration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Slf4j
class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider provider;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LoginService loginService;

    @Test
    @DisplayName("Validate Token")
    void validateToken() throws Exception {
        //given
        String loginId = "NewMem1";
        String password = "newMember123";
        LoginDto loginDto = loginService.loginProcess(loginId, password);
        String token = loginDto.getToken();
        String refreshToken = loginDto.getRefreshToken();
        //when
        JwtTokenProvider.JwtCode validateToken = provider.validateToken(token);
        JwtTokenProvider.JwtCode validateRefresh = provider.validateToken(refreshToken);
        //then
        Assertions.assertThat(validateToken).isEqualTo(JwtTokenProvider.JwtCode.ACCESS);
        Assertions.assertThat(validateRefresh).isEqualTo(JwtTokenProvider.JwtCode.ACCESS);

    }

    @Test
    @DisplayName("Test resolveToken")
    void resolveToken() throws Exception {
        //given
        String loginId = "NewMem1";
        String password = "newMember123";
        LoginDto loginDto = loginService.loginProcess(loginId, password);

        //when
        Enumeration<String> authorization = mockMvc.perform(get("/login/test")
                        .header("Authorization", "barer-" + loginDto.getToken()))
                .andReturn().getRequest().getHeaderNames();

        log.info("authorization={}",authorization);
        //then

    }
}