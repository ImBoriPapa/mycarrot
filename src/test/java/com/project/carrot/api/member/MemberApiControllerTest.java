package com.project.carrot.api.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.carrot.api.member.form.request.RequestRegisterForm;
import com.project.carrot.domain.member.dto.RegisteMemberDto;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.exception.errorCode.ErrorCode;
import com.project.carrot.utlis.header.ResponseHeader;
import com.project.carrot.utlis.response.CustomResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Slf4j
@Transactional
@AutoConfigureMockMvc
class MemberApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 조회 성공 테스트 - GET:/member")
    void searchAllMember() throws Exception {
        //given
        mockMvc.perform(get("/api/member")
                        .accept(ResponseHeader.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status").value(CustomResponseStatus.SUCCESS.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SUCCESS.getMessage()))
                .andExpect(jsonPath("result").isArray())
                .andExpect(jsonPath("result").hasJsonPath())
                .andExpect(jsonPath("$.result[0].memberId").value(1L))
                .andExpect(jsonPath("$.result[0].loginId").value("test1"));
        //when

        //then

    }

    @Test
    @DisplayName("회원 등록 성공 테스트 - POST:/member")
    void successRegisterMember() throws Exception {
        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiLogin");
        form.setPassword("api7348!@");
        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/member")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues(HttpHeaders.ACCEPT, ResponseHeader.APPLICATION_JSON))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(content().contentType(ResponseHeader.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("status").value(CustomResponseStatus.SUCCESS.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result.memberId").exists())
                .andExpect(jsonPath("$.result.signUpDate").exists())
                .andExpect(jsonPath("$.result.links").isArray())
                .andExpect(jsonPath("$.result.links[0].rel").value("Find All Member"))
                .andExpect(jsonPath("$.result.links[1].rel").value("To Detail of Member"))
                .andExpect(jsonPath("$.result.links[2].rel").value("To Update of Member"))
                .andExpect(jsonPath("$.result.links[3].rel").value("To delete of Member"));
        //when

        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트 - POST:/member LoginId = 공백 ")
    void failRegisterMember_1() throws Exception {

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("");
        form.setPassword("api7348!@");
        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/member")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").isArray());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트 - POST:/member LoginId = 중복 ")
    void failRegisterMember_2() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("cszc7348!@")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester");
        form.setPassword("api7348!@");
        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/member")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.DUPLICATE_LOGIN_ID.code))
                .andExpect(jsonPath("$.result.errorMessage").value(ErrorCode.DUPLICATE_LOGIN_ID.message));
        //when
        //then

    }
}