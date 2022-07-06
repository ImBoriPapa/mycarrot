package com.project.carrot.domain.service;

import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import com.project.carrot.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Test
    public void 회원가입() throws Exception {
        //given
        Member saveMember = new Member.MemberBuilder()
                .userId("member1")
                .password("1234")
                .nickname("membermember")
                .email("member@member.com")
                .signUpdate(LocalDateTime.now())
                .builder();

        //when
        Long saveResult = memberService.saveMember(saveMember);
        Optional<Member> byId = memberRepository.findById(saveResult);
        //then
        assertThat(saveResult).isEqualTo(byId.get().getMemberId());
    }

    @Test
    public void 회원_비밀번호_검증() throws Exception {
        //given
        Member saveMember = new Member.MemberBuilder()
                .userId("member1")
                .password("1234")
                .nickname("membermember")
                .email("member@member.com")
                .signUpdate(LocalDateTime.now())
                .builder();

        //when
        Long saveResult = memberService.saveMember(saveMember);
        Member checkPassword = memberService.checkIdAndPw("member1","1234");
        //then
        assertThat(checkPassword.getMemberId()).isEqualTo(saveResult);

    }

    @Test
    public void 회원목록() throws Exception {
        //given
        Member member1 = new Member.MemberBuilder().userId("member1").nickname("nickname1").builder();
        Member member2 = new Member.MemberBuilder().userId("member2").nickname("nickname2").builder();
        Member member3 = new Member.MemberBuilder().userId("member3").nickname("nickname3").builder();
        Member member4 = new Member.MemberBuilder().userId("member4").nickname("nickname4").builder();
        Member member5 = new Member.MemberBuilder().userId("member5").nickname("nickname5").builder();
        //when
        Long saveMember1 = memberService.saveMember(member1);
        Long saveMember2 = memberService.saveMember(member2);
        Long saveMember3 = memberService.saveMember(member3);
        Long saveMember4 = memberService.saveMember(member4);
        Long saveMember5 = memberService.saveMember(member5);

        List<Member> members = memberService.members();
        //then
        for (Member member : members) {
            System.out.println("members.getUserId = "+member.getUserId()+" , getNickname = "+member.getNickname());
        }

    }

    @Test
    public void 회원아이디중복체크() throws Exception {
        //given
        Member member = new Member.MemberBuilder().userId("member1").builder();

        //when
        Long saveMember = memberService.saveMember(member);
        boolean result = memberService.validationDuplicateUserId("member1");
        //then
        System.out.println("validationDuplicateUserId result = " + result);
    }
}