package com.project.carrot.web.controller.member;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {

        List<Address> address = new ArrayList<>();
        address.add(new Address("서울시","우장산","1동"));

        Member newMember = Member.builder()
                .loginId("dari")
                .password("cszc7348!@")
                .nickname("darida")
                .contact("010-1111-1111")
                .email("dari@dari.com")
                .address(address).build();

        memberService.saveMember(newMember);
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }



    @DisplayName("회원가입 페이지 뷰 테스트")
    @Test
    public void signUpForm() throws Exception {
        //given
        mockMvc.perform(get("/member/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/signUpForm"))
                .andExpect(model().attributeExists("createMemberForm"));

        //when

        //then

    }

    @DisplayName("로그인 성공 테스트")
    @Test
    void login_success() throws Exception {
        Optional<Member> result = memberRepository.findByNickname("darida");
        mockMvc.perform(post("/member/login")
                        .param("loginId", "dari")
                        .param("password", "cszc7348!@"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/success"))
                .andExpect(authenticated().withUsername(result.get().getMemberId().toString()));
    }

    @DisplayName("로그인 실패 테스트")
    @Test
    void login_fail() throws Exception {

        String message = "";
        message = "비밀번호와 아이디를 확인해주세요";
        message = URLEncoder.encode(message, "UTF-8");

        mockMvc.perform(post("/member/login")
                        .param("loginId", "dari123")
                        .param("password", "cszc7348!@123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login?error=true&&message="+message))
                .andExpect(unauthenticated());
    }

    @DisplayName("로그아웃 테스트")
    @Test
    void logout()throws Exception {
        mockMvc.perform(post("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated());
    }
}