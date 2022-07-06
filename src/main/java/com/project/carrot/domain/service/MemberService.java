package com.project.carrot.domain.service;


import com.project.carrot.domain.entity.Member;
import com.project.carrot.dto.MemberDto;
import com.project.carrot.dto.MemberList;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {
    boolean validationDuplicateUserId(String userId);//아이디가 존재하는지 확인한다.

    boolean validationDuplicateEmail(String email);//비밀번호가 존재하는지 확인한다.

    Member checkIdAndPw(String userId , String password);  // 아이디와 비밀번호로 존재하는 회원인지 확인

    Long saveMember(Member member); //회원정보 저장

    List<Member> members();
}
