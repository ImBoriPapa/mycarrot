package com.project.carrot.domain.service;


import com.project.carrot.dto.MemberDTO;
import com.project.carrot.domain.entity.Member;

import java.util.ArrayList;

public interface MemberService {
    boolean checkUserId(String userId);//아이디가 존재하는지 확인한다.

    boolean checkEmail(String email);//비밀번호가 존재하는지 확인한다.

    boolean checkIdAndPw(String id , String pw);  // 아이디와 비밀번호로 존재하는 회원인지 확인

    void saveMember(MemberDTO memberDTO);

    ArrayList<MemberDTO> memberList(ArrayList<Member> member);
}
