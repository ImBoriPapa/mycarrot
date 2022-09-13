package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.member.reposiotory.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
@Slf4j
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("")
    void addressTest() throws Exception{
        //given

        //when

        //then

    }

}