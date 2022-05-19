package com.project.carrot.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.entity.Member;
import com.project.carrot.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @Test
    public void  login()throws Exception
    {
        //given
        MemberDTO save = new MemberDTO();
        save.setMember_id("dari");
        save.setMember_password("1234");
        save.setMember_nickname("nickname!!");
        Long id = save.getId();
        String member_id = save.getMember_id();
        String member_password= save.getMember_password();
        String member_nickname= save.getMember_nickname();
        Member member = new Member(id,member_id,member_password,member_nickname);
        memberRepository.save(member);

        MemberDTO join = new MemberDTO();
        join.setMember_id("dari");
        join.setMember_password("1234");
        //when
        List<Member> list = memberRepository.findByMemberIdAndMemberPassword(join.getMember_id(), join.getMember_password());

        //then
        for (Member member1 : list) {
            System.out.println("member1 = " + member1);
        }

    }
}