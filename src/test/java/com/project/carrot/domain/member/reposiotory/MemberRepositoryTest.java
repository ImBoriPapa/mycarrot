package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원정보 저장")
    public void save() throws Exception {
        //given
        Member testA =  Member.builder()
                .loginId("testId")
                .password("!@#$1234")
                .nickname("testNick")
                .email("test@test.com")
                .signUpDate(LocalDateTime.now())
                .build();
        //when
        Member saveMember = memberRepository.save(testA);
        Optional<Member> findMember = memberRepository.findByMemberId(saveMember.getMemberId());

        //then
        Assertions.assertThat(saveMember.getMemberId()).isEqualTo(findMember.orElse(null).getMemberId());
    }

}