package com.project.carrot.api.member;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.utlis.header.ResponseHeader;
import com.project.carrot.utlis.response.CustomResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Slf4j
@Transactional
@AutoConfigureMockMvc
class MemberApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원 저장 성공 테스트 - GET:/member")
    void registerMember() throws Exception{
        //given
        List<Member> memberList = memberService.findMemberList();
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
    @DisplayName("회원 저장 실패 테스트 - GET:/member")
    void failRegisterMember() throws Exception{
        //given

        //when

        //then

    }

}