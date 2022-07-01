package com.project.carrot.domain.service;

import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import com.project.carrot.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void saveMember() throws Exception
    {
        //given
        MemberDTO memberDTO = new MemberDTO(0L,"member2","#1234","member2","member@member.com",LocalDateTime.now(),null);

        Member member = new Member.MemberBuilder(memberDTO).signUpdate(LocalDateTime.now()).builder();

        //when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByUserId("member2");
        //then
        Assertions.assertThat(memberDTO.getMemberId()).isEqualTo(findMember.get().getMemberId());
        Assertions.assertThat(member.getSignUpDate()).isEqualTo(findMember.get().getSignUpDate());

    }

    @Test
    public void idCheck() throws Exception
    {
        //given
        MemberDTO memberDTO = new MemberDTO(0L,"member2","#1234","member2","member@member.com",LocalDateTime.now(),null);

        Member member = new Member.MemberBuilder(memberDTO).signUpdate(LocalDateTime.now()).builder();

        //when

        memberRepository.save(member);
        Optional<Member> findMemberId = memberRepository.findByUserId("member2");

        //then
        Assertions.assertThat(findMemberId.get().getMemberId()).isEqualTo("member2");
        Assertions.assertThat(findMemberId.isPresent()).isEqualTo(true);

    }

    @Test
    public void checkId() throws Exception
    {
        //given
        MemberDTO memberDTO = new MemberDTO(0L,"member2","#1234","member2","member@member.com",LocalDateTime.now(),null);

        Member member = new Member.MemberBuilder(memberDTO).signUpdate(LocalDateTime.now()).builder();

        //when
        memberService.saveMember(memberDTO);
        boolean member2 = memberService.checkUserId("member2");
        //then
        Assertions.assertThat(member2).isTrue();

    }
}