package com.project.carrot.domain.member.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    public MemberService memberService;

    @Autowired
    public MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 테스트")
    public void saveMemberTest() throws Exception {
        //given
        Member testA = new Member.MemberBuilder() // 중복값이 없는 상황
                .loginId("testId")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test@test.com")
                .signUpdate(LocalDateTime.now())
                .builder();

        Member duplicateLoginId = new Member.MemberBuilder() // loginId가 중복인 상황
                .loginId("testId")
                .password("!@#$1234")
                .nickname("test")
                .email("test")
                .signUpdate(LocalDateTime.now())
                .builder();

        Member duplicateNickname = new Member.MemberBuilder() // nickname 이 중복인 상황
                .loginId("testId123")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test")
                .signUpdate(LocalDateTime.now())
                .builder();

        Member duplicateEmail = new Member.MemberBuilder() // email 이 중복인 상황
                .loginId("testId123")
                .password("!@#$1234")
                .nickname("testNick123")
                .email("test@test.com")
                .signUpdate(LocalDateTime.now())
                .builder();
        //when
        Member saveMember = memberService.saveMember(testA);
        Optional<Member> findMember = memberRepository.findByMemberId(saveMember.getMemberId());


        //then
        assertThat(saveMember.getMemberId()).isEqualTo(findMember.get().getMemberId()); //성공로직

        assertThrows(IllegalStateException.class,()->{ //login 아이디 중복 실패
            Member duplicateIdMember = memberService.saveMember(duplicateLoginId);
        });

        assertThrows(IllegalStateException.class,()->{ //nickname 중복 실패
            Member duplicateNickMember = memberService.saveMember(duplicateNickname);
        });

        assertThrows(IllegalStateException.class,()->{ //email 중복 실패
            Member duplicateEmailMember = memberService.saveMember(duplicateEmail);
        });



    }

}