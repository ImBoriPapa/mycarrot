package com.project.carrot.domain.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean checkUserId(String userId) {//회원아이디가 존재하면 true 없으면 false 반환
        Optional<Member> exitsId = memberRepository.findByUserId(userId);
        return exitsId.isPresent();
    }

    @Override
    public boolean checkEmail(String email){ //이메일이 존재하면 true 없으면 false
        Optional<Member> exitsEmail = memberRepository.findByEmail(email);
        return exitsEmail.isPresent();
    }

    @Override
    public boolean checkIdAndPw(String id,String pw) { //아이디와 비밀번호로 존재하는 회원이면 true 아니면 false
        Optional<Member> checkByMemberId = memberRepository.findByUserId(id);
        Optional<String> checkId = Optional.ofNullable(checkByMemberId.orElse(new Member()).getUserId());
        Optional<String> checkPw = Optional.ofNullable(checkByMemberId.orElse(new Member()).getPassword());

        if (checkId.equals(id) && checkPw.equals(pw)) {
            return true;
        }
        return false;
        }

    @Override
    public void saveMember(MemberDTO memberDTO) { //회원정보 저장
        Member member = new Member
                .MemberBuilder(memberDTO)
                .signUpdate(LocalDateTime.now())
                .builder();
        memberRepository.save(member);
    }

    @Override
    public ArrayList<MemberDTO> memberList(ArrayList<Member> member) {

        return null;
    }

}
