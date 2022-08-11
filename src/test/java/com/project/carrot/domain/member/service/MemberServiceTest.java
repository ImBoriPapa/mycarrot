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
import java.util.ArrayList;
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
        Member testA =  Member.builder() // 중복값이 없는 상황
                .loginId("testId")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test@test.com")
                .address(new ArrayList<>())
                .build();

        Member duplicateLoginId =  Member.builder() // loginId가 중복인 상황
                .loginId("testId")
                .password("!@#$1234")
                .nickname("test")
                .email("test")
                .address(new ArrayList<>())
                .build();

        Member duplicateNickname =  Member.builder() // nickname 이 중복인 상황
                .loginId("testId123")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test")
                .address(new ArrayList<>())
                .build();

        Member duplicateEmail =  Member.builder() // email 이 중복인 상황
                .loginId("testId123")
                .password("!@#$1234")
                .nickname("testNick123")
                .email("test@test.com")
                .address(new ArrayList<>())
                .build();
        //when
        Long saveMember = memberService.saveMember(testA);
        Optional<Member> findMember = memberRepository.findByMemberId(saveMember);


        //then
        assertThat(saveMember).isEqualTo(findMember.get().getMemberId()); //성공로직

    }

    @Test
    public void saveAddress() throws Exception{

        //given
        ArrayList address = new ArrayList();
        address.add(new Address("서울시", "강서구", "우장산동"));

        Member testA =  Member.builder()
                .loginId("dari1234")
                .password("!@#$1234")
                .nickname("testNick123")
                .email("test232@test.com")
                .memberRoll(MemberRoll.USER)
                .address(address)
                .build();

        //when
        Long id = memberService.saveMember(testA);
        Member member = memberService.findMember(id);

        //then
        assertThat(member.getMemberId()).isEqualTo(id);
        assertThat(member.getAddress()).isEqualTo(testA.getAddress());

    }

}