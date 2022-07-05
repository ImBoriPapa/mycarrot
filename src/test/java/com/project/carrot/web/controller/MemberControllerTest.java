package com.project.carrot.web.controller;

import com.project.carrot.domain.service.MemberService;
import com.project.carrot.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MemberControllerTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberController memberController;


    @Test
    public void idCheck()  throws Exception
    {
        //given
        MemberDto memberDTO = new MemberDto(0L,"member2","#1234","member2","member@member.com", LocalDateTime.now(),null);
        memberService.saveMember(memberDTO);

        //when
        MemberDto member = new MemberDto(0L,"member2","#1234","member2","member@member.com", LocalDateTime.now(),null);


        //then

    }

}