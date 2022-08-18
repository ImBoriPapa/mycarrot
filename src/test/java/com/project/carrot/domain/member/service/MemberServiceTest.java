package com.project.carrot.domain.member.service;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.entity.MemberRoll;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("회원 저장 테스트")
    public void saveMemberTest() throws Exception {
        //given

        List<Address> address = new ArrayList<>();
        address.add(new Address("서울시","우장산","1동"));

        CreateMemberForm createMemberForm = new CreateMemberForm();
        createMemberForm.setLoginId("dari1234");
        createMemberForm.setPassword("!@#$1234");
        createMemberForm.setNickname("testNick123");
        createMemberForm.setEmail("test232@test.com");
        createMemberForm.setAddress(address);

        //when
        Long saveMember = memberService.saveMember(createMemberForm);
        Optional<Member> findMember = memberRepository.findByMemberId(saveMember);

        //then
        assertThat(saveMember).isEqualTo(findMember.get().getMemberId()); //성공로직
        assertThat(findMember.get().getAddress().get(0)).isEqualTo(address.get(0));
        assertThat(findMember.get().getAddress().contains(address.get(0))).isTrue();

    }

    @Test
    @DisplayName("비밀번호 인코더 적용")
    public void passwordEncoder() {
        //given

        List<Address> address = new ArrayList<>();
        address.add(new Address("경기도","김포시","사우동"));

        CreateMemberForm createMemberForm = new CreateMemberForm();
        createMemberForm.setLoginId("dari");
        createMemberForm.setPassword("!@#$1234");
        createMemberForm.setNickname("test2");
        createMemberForm.setEmail("test2@test.com");
        createMemberForm.setAddress(address);

        //when
        Long saveMember = memberService.saveMember(createMemberForm);
        Optional<Member> findMember = memberRepository.findByMemberId(saveMember);

        //then
        assertThat(findMember.get().getPassword()).isNotEqualTo("!@#$1234"); //성공로직
        assertThat(passwordEncoder.matches("!@#$1234", findMember.get().getPassword())).isTrue();
        assertThat(passwordEncoder.matches("!@#$134", findMember.get().getPassword())).isFalse();//실패 로직
    }
}