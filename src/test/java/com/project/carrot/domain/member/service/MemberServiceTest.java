package com.project.carrot.domain.member.service;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.entity.MemberRoll;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("회원 저장 테스트")
    public void saveMemberTest() throws Exception {
        //given
        Member testA = new Member.MemberBuilder() // 중복값이 없는 상황
                .loginId("testId")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test@test.com")
                .builder();

        Member duplicateLoginId = new Member.MemberBuilder() // loginId가 중복인 상황
                .loginId("testId")
                .password("!@#$1234")
                .nickname("test")
                .email("test")
                .builder();

        Member duplicateNickname = new Member.MemberBuilder() // nickname 이 중복인 상황
                .loginId("testId123")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test")
                .builder();

        Member duplicateEmail = new Member.MemberBuilder() // email 이 중복인 상황
                .loginId("testId123")
                .password("!@#$1234")
                .nickname("testNick123")
                .email("test@test.com")
                .builder();
        //when
        Long saveMember = memberService.saveMember(testA);
        Optional<Member> findMember = memberRepository.findByMemberId(saveMember);


        //then
        assertThat(saveMember).isEqualTo(findMember.get().getMemberId()); //성공로직

    }

    @Test
    public void saveAddress() throws Exception{
        //given
        Member testA = new Member.MemberBuilder()
                .loginId("dari1234")
                .password("!@#$1234")
                .nickname("testNick123")
                .email("test232@test.com")
                .memberRoll(MemberRoll.USER)
                .address(new Address("서울","강서구","우장산동"))
                .builder();

        //when
        Long id = memberService.saveMember(testA);
        Member member = memberService.findMember(id);

        //then
        assertThat(member.getMemberId()).isEqualTo(id);

    }

}