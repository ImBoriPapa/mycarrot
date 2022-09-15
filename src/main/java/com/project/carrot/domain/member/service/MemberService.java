package com.project.carrot.domain.member.service;


import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressDataRepository;
import com.project.carrot.domain.member.dto.RegisteMemberDto;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.customEx.IncorrectAddressCodeException;
import com.project.carrot.exception.customEx.NoExistMemberException;
import com.project.carrot.exception.errorCode.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
     *
     * @param dto
     * @return
     */
    public Member createMember(RegisteMemberDto dto) {
        if (dto.getAddressCode() < 1000) {
            throw new IncorrectAddressCodeException(ErrorCode.INCORRECT_ADDRESS_CODE);
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Optional<AddressData> data = addressDataRepository.findByAddressCode(dto.getAddressCode());
        List<AddressData> addressData = data.stream().collect(Collectors.toList());

        Member createdMember = Member.CreateMember()
                .loginId(dto.getLoginId())
                .password(encodedPassword)
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .address(addressData)
                .build();

        return memberRepository.save(createdMember);
    }

    public void updateMember(Long id, Member updateParam) {
        Optional<Member> findMember = memberRepository.findByMemberId(id);
        findMember.orElseThrow(() -> new IllegalArgumentException("회원정보가 없습니다.")).modifiedMember(updateParam);
    }

    /**
     * @param pageable
     * @return
     */
    public Page<Member> findMemberList(Pageable pageable) {
        PageRequest request = PageRequest.of(pageable.getPageNumber(), 20, Sort.Direction.DESC, "memberId");
        Page<Member> members = memberRepository.findAll(request);
        if (members.getContent().size() == 0) {
            throw new NoExistMemberException(ErrorCode.NO_EXIST_MEMBER);
        }
        return members;
    }

    //회원 목록 아이디로 조회
    public Member findMember(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(() -> new NoExistMemberException(ErrorCode.NO_EXIST_MEMBER));
    }


    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
