package com.project.carrot.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.entity.Member;
import com.project.carrot.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() throws Exception
    {
        //given
        MemberDTO dto = new MemberDTO();
        dto.setMember_id("id");
        dto.setMember_password("pw");
        dto.setMember_nickname("nickname");
        Long id = dto.getId();
        String member_id = dto.getMember_id();
        String member_password= dto.getMember_password();
        String member_nickname= dto.getMember_nickname();

        Member member = new Member(id,member_id,member_password,member_nickname);


        //when
        Member save = memberRepository.save(member);
        Optional<Member> find = memberRepository.findById(save.getId());
        //then
        assertThat(save.getId()).isEqualTo(find.get().getId());
        assertThat(save).isEqualTo(find);
        System.out.println("find = " + find.get().getId());
        System.out.println("save = " + save.getId());


    }
}