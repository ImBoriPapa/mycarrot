package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.dto.MemberForm;

import java.util.Optional;

public interface CustomMemberRepository {

    Optional<MemberForm> findAuthorizationInfoByLoginId(String LoginId);
    Optional<MemberForm> findAuthorizationInfoByMemberId(Long memberId);

}
