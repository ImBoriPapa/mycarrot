package com.project.carrot.domain.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean checkExitsId(String memberId) {//회원아이디가 존재하면 true 없으면 false 반환
       Optional<Member> exitsId = memberRepository.findByMemberId(memberId);
        if(exitsId.isPresent()){

            return true;
        }else
            return false;
        }

    @Override
    public boolean checkIdAndPw(String id,String pw) { //아이디와 비밀번호로 존재하는 회원이면 true 아니면 false
        Optional<Member> checkByMemberId = memberRepository.findByMemberId(id);
        Optional<String> checkId = Optional.ofNullable(checkByMemberId.orElse(new Member()).getMemberId());
        Optional<String> checkPw = Optional.ofNullable(checkByMemberId.orElse(new Member()).getMemberPassword());

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
