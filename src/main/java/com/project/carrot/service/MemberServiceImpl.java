package com.project.carrot.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.entity.Member;
import com.project.carrot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void join(MemberDTO dto) {
       Long id = dto.getId();
       String member_id = dto.getMember_id();
       String member_password =   dto.getMember_password();
       String member_nickname =  dto.getMember_nickname();

        Member member = new Member(id,member_id,member_password,member_nickname);

        memberRepository.save(member);
    }

    @Override
    public void login(MemberDTO dto) {
        String memberId = dto.getMember_id();
        String memberPw = dto.getMember_password();

        List<Member> result = memberRepository.findByMemberIdAndMemberPassword(memberId,memberPw);

    }
}
