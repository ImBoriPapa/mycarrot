package com.project.carrot.service;

import com.project.carrot.entity.Member;
import com.project.carrot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean checkExitsId(String memberId) {//회원아이디가 존재하지 않으면 null
       Optional<Member> exitsId = memberRepository.findByMemberId(memberId);
        if(!exitsId.isPresent()){
            return false;
        }else
            return true;


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
}
