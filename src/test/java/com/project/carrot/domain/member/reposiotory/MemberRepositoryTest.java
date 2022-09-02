package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.Member;
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
    @DisplayName("차이점을 찾아보자")
    public void findDifference() throws Exception{
        String loginId = "test";
        //given
        Optional<Member> findByLoginId = memberRepository.findByLoginId(loginId);

        //when


        //then


    }

}