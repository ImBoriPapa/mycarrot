package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.dto.MemberForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버폼 테스트")
    void memberForm() throws Exception{
        //given
        String loginId = "test1";
        //when
        Optional<MemberForm> memberFormByLoginId = memberRepository.findAuthorizationInfoByLoginId(loginId);

        //then
        System.out.println("loginID getMemberId="+memberFormByLoginId.get().getMemberId());
        System.out.println("loginID getLoginId="+memberFormByLoginId.get().getLoginId());
        System.out.println("loginID getPassword="+memberFormByLoginId.get().getPassword());
        System.out.println("loginID getMemberRoll="+memberFormByLoginId.get().getMemberRoll());


    }

}