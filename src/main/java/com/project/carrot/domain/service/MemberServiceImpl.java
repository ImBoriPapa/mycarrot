package com.project.carrot.domain.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import com.project.carrot.dto.MemberList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;


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
    public MemberDTO checkIdAndPw(String userid,String password) { //아이디와 비밀번호로 존재하는 회원이면 true 아니면 false
//        Optional<Member> checkByMemberId = memberRepository.findByUserId(id);
//        Optional<String> checkId = Optional.ofNullable(checkByMemberId.orElse(new Member()).getUserId());
//        Optional<String> checkPw = Optional.ofNullable(checkByMemberId.orElse(new Member()).getPassword());
//
//        if (checkId.equals(id) && checkPw.equals(pw)) {
//            return true;
//        }
//        return false;
        return memberRepository.findByUserId(userid)
                .filter(m -> m.getPassword().equals(password))
                 .map(m->new MemberDTO(m))
                        .orElse(null);
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
    public ArrayList<MemberList> members() { //회원 목록 조회 (memberId,userId,nickName,signUpDate)
        List<Member> members = memberRepository.findAll();
        ArrayList<MemberList> memberList = new ArrayList<>();

        for (Member memberData : members) {
            MemberList list = new MemberList(memberData.getMemberId(), memberData.getUserId(), memberData.getNickname(), memberData.getSignUpDate());
            memberList.add(list);
        }
        return memberList;
    }

}
