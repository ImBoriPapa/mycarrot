package com.project.carrot.repository;

import com.project.carrot.domain.repository.MemberRepository;
import com.project.carrot.domain.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() throws Exception
    {
        //given
        Member member = new Member();


        //when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByMemberId("memberId");
        //then
        Assertions.assertThat(member.getMemberId()).isEqualTo(findMember.get().getMemberId());




    }



}