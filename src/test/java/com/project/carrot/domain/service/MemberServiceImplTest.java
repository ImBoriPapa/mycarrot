package com.project.carrot.domain.service;

import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import com.project.carrot.dto.MemberDTO;
import com.project.carrot.dto.MemberList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Test
    public void checkIdAndPw() throws Exception
    {
        //given
        MemberDTO toEntity = new MemberDTO(0L,"member2","#1234","member2","member@member.com",LocalDateTime.now(),null);

        Member saveMember = new Member.MemberBuilder(toEntity).signUpdate(LocalDateTime.now()).builder();

        //when
        MemberDTO toDTO = memberService.checkIdAndPw("member2", "#1234");
        //then
        System.out.println("toDTO.getUserId() = " + toDTO.getUserId());

    }

    @Test
    public void memberList() throws Exception
    {
        //given
        MemberDTO member1 = new MemberDTO(0L,"member1","#1234","member1","member1@member.com",LocalDateTime.now(),null);
        MemberDTO member2 = new MemberDTO(0L,"member2","#1234","member2","member2@member.com",LocalDateTime.now(),null);
        MemberDTO member3 = new MemberDTO(0L,"member3","#1234","member3","member3@member.com",LocalDateTime.now(),null);
        memberService.saveMember(member1);
        memberService.saveMember(member2);
        memberService.saveMember(member3);

        //when
        ArrayList<MemberList> memberLists = memberService.members();
        //then
        for (MemberList memberList : memberLists) {
            System.out.println("memberList.getUserId() = " + memberList.getUserId()+", memberList.getNickname() = "+memberList.getNickname());
        }

    }
}