package com.project.carrot.service;


import com.project.carrot.dto.MemberDTO;
import com.project.carrot.entity.Member;

import java.util.ArrayList;
import java.util.Optional;

public interface MemberService {
    boolean checkExitsId(String memberId);//아이디가 존재하는지 확인한다.
    boolean checkIdAndPw(String id , String pw);  // 아이디와 비밀번호로 존재하는 회원인지 확인
    void saveMember(MemberDTO memberDTO);
    ArrayList<MemberDTO> memberList(ArrayList<Member> member);
}
