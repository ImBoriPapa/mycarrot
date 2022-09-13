package com.project.carrot.domain.member.service;


import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressDataRepository;
import com.project.carrot.domain.member.dto.CreateMemberDto;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.errorCode.ErrorCode;
import com.project.carrot.exception.customEx.NotExistMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressDataRepository addressDataRepository;

    /**
     * 회원 정보 저장 기능
     * password encoding 후 저장
     * @param dto
     * @return
     */
    public Long saveMember(CreateMemberDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Optional<AddressData> data = addressDataRepository.findById(dto.getAddress());
        List<AddressData> addressData = data.stream().collect(Collectors.toList());

        Member createdMember = Member.CreateMember()
                .loginId(dto.getLoginId())
                .password(encodedPassword)
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .address(addressData)
                .build();

        return memberRepository.save(createdMember).getMemberId();
    }

    public void updateMember(Long id,Member updateParam) {
        Optional<Member> findMember = memberRepository.findByMemberId(id);
        findMember.orElseThrow(()->new IllegalArgumentException("회원정보가 없습니다.")).modifiedMember(updateParam);
    }

    //회원 전체 목록 조회
    public List<Member> findMemberList() {
        return memberRepository.findAll();
    }

    //회원 목록 아이디로 조회
    public Member findMember(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(() -> new NotExistMemberException(ErrorCode.NO_EXIST_MEMBER));
    }


    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
