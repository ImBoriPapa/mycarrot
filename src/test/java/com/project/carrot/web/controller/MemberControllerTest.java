package com.project.carrot.web.controller;

import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.service.MemberService;
import com.project.carrot.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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
        MemberDTO memberDTO = new MemberDTO(0L,"member2","#1234","member2","member@member.com", LocalDateTime.now(),null);
        memberService.saveMember(memberDTO);

        //when
        MemberDTO member = new MemberDTO(0L,"member2","#1234","member2","member@member.com", LocalDateTime.now(),null);


        //then

    }

}