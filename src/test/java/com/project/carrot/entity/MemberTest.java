package com.project.carrot.entity;


import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import com.project.carrot.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void find() throws Exception
    {
        //given
        MemberDto memberDTO = new MemberDto();
        memberDTO.setUserId("member1");
        memberDTO.setPassword("1234");
        memberDTO.setNickname("memberNick");
        memberDTO.setEmail("member@member.com");

        Member member = new Member.MemberBuilder(memberDTO).builder();
        //when
        Member save = memberRepository.save(member);
        Optional<Member> result = memberRepository.findByUserId("member1");
        //then
        Assertions.assertThat(result.get().getUserId()).isEqualTo("member1");

        System.out.println("result.get().getUserId() = " + result.get().getUserId());
        System.out.println("result.get().getPassword() = " + result.get().getPassword());
        System.out.println("result.get().getNickname() = " + result.get().getNickname());
        System.out.println("result.get().getEmail() = " + result.get().getEmail());

        }

        }


