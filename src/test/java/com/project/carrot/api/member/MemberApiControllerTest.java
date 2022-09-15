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
    @DisplayName("회원 전체 조회 성공 테스트 - GET:/members")
    void successSearchOneMember() throws Exception {

        //given
            for (int i = 1; i < 50; i++) {
                int address = 1000;
                RegisteMemberDto dto = RegisteMemberDto.builder()
                        .loginId("NewMember" + i)
                        .password("newMember123")
                        .nickname("mooo" + i)
                        .email("newMoo@new.com" + i)
                        .contact("010-1232-23" + i)
                        .addressCode(address + i)
                        .build();
                memberService.createMember(dto);
            }


        mockMvc.perform(get("/api/members")
                        .accept(ResponseHeader.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().stringValues(HttpHeaders.ACCEPT, ResponseHeader.APPLICATION_JSON))
                .andExpect(content().contentType(ResponseHeader.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("status").value(CustomResponseStatus.SUCCESS.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result.totalPage").exists())
                .andExpect(jsonPath("$.result.hasPrevious").exists())
                .andExpect(jsonPath("$.result.hasNext").exists())
                .andExpect(jsonPath("$.result.currentPageNumber").exists())
                .andExpect(jsonPath("$.result.nextPageNumber").exists())
                .andExpect(jsonPath("$.result.previousPageNumber").exists())
                .andExpect(jsonPath("$.result.memberList").isArray())
                .andExpect(jsonPath("$.result.memberList").exists());
        //when

        //then

    }

    @Test
    @DisplayName("회원 전체 조회 실패 테스트 - GET:/members 저장된 회원이 없는 경우")
    void failSearchOneMember() throws Exception {
        //given

        mockMvc.perform(get("/api/members/100")
                        .accept(ResponseHeader.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        //when

        //then

    }

    @Test
    @DisplayName("회원 등록 성공 테스트 - POST:/members")
    void successRegisterMember() throws Exception {
        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiLogin");
        form.setPassword("api7348!@");
        form.setNickname("apiTest");
        form.setContact("010-2222-3333");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
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
                .andExpect(jsonPath("$.result.links[0].rel").value("GET: Find All Member"))
                .andExpect(jsonPath("$.result.links[1].rel").value("GET: Find One Member"))
                .andExpect(jsonPath("$.result.links[2].rel").value("PUT: Update of Member"))
                .andExpect(jsonPath("$.result.links[3].rel").value("DELETE: delete of Member"));
        //when

        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트1 - POST:/members LoginId = 공백 ")
    void failRegisterMember_1() throws Exception {

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("");
        form.setPassword("api7348!@");
        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
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
    @DisplayName("회원 등록 실패 테스트2 - POST:/members LoginId = 중복 ")
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
        mockMvc.perform(post("/api/members")
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

    @Test
    @DisplayName("회원 등록 실패 테스트3 - POST:/members password = 공백 ")
    void failRegisterMember_3() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("api7348")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("");
        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트4 - POST:/members password = 최소 길이 미만 ")
    void failRegisterMember_4() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("api7348")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("1111");
        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트5 - POST:/members nickname = 닉네임 누락 ")
    void failRegisterMember_5() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("api7348")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
//        form.setNickname("apiTest");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트6 - POST:/members nickname = 닉네임 중복 ")
    void failRegisterMember_6() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("api7348")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
        form.setNickname("apiTester");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.DUPLICATE_NICK_NAME.code))
                .andExpect(jsonPath("$.result.errorMessage").value(ErrorCode.DUPLICATE_NICK_NAME.message));
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트7 - POST:/members nickname = 닉네임 공백 ")
    void failRegisterMember_7() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("api7348")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
        form.setNickname("");
        form.setEmail("api@api.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트8 - POST:/members email = 형식에 맞지 않는 이메일 ")
    void failRegisterMember_8() throws Exception {

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
        form.setNickname("apiTest");
        form.setEmail("한글이메일");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트9 - POST:/members email = 이메일 중복 ")
    void failRegisterMember_9() throws Exception {

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId("apiTester")
                .password("api7348")
                .nickname("apiTester")
                .email("apitester@test.com")
                .contact("010-0101-010")
                .addressCode(1002).build();
        memberService.createMember(registeMemberDto);

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
        form.setNickname("닉네임이다");
        form.setEmail("apitester@test.com");
        form.setAddressCode(1001);
        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.DUPLICATE_EMAIL.code))
                .andExpect(jsonPath("$.result.errorMessage").value(ErrorCode.DUPLICATE_EMAIL.message));
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트10 - POST:/members addressCode = 주소코드 누락 ")
    void failRegisterMember_10() throws Exception {

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
        form.setNickname("닉네임이다");
        form.setEmail("apitester@test.com");

        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }

    @Test
    @DisplayName("회원 등록 실패 테스트11 - POST:/members addressCode = 잘못된 주소값 ")
    void failRegisterMember_11() throws Exception {

        //given
        RequestRegisterForm form = new RequestRegisterForm();
        form.setLoginId("apiTester2");
        form.setPassword("api7348");
        form.setNickname("닉네임이다");
        form.setEmail("apitester@test.com");
        form.setAddressCode(999);

        String requestValue = objectMapper.writeValueAsString(form);
        mockMvc.perform(post("/api/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestValue)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.name()))
                .andExpect(jsonPath("message").value(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL.getMessage()))
                .andExpect(jsonPath("$.result.errorType").exists())
                .andExpect(jsonPath("$.result.errorCode").value(ErrorCode.VALID_FAIL_ERROR.code))
                .andExpect(jsonPath("$.result.errorMessage").exists());
        //when
        //then

    }
}