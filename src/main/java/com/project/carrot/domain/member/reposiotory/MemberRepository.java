package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.dto.MemberForm;
import com.project.carrot.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {

    Optional<Member> findByMemberId(Long memberId);// 단건 조회

    Page<Member> findAll(Pageable pageable);//전체 회원 조회

    boolean existsByLoginId(String loginId);

    boolean existsByNickname(String loginId);

    boolean existsByEmail(String loginId);

    /**
     * 로그인에 필요한 정보만 조회하기 위한 기능
     *
     * @param LoginId
     * @return memberId, loginId, password, memberRoll
     */
    @Override
    Optional<MemberForm> findAuthorizationInfoByLoginId(String LoginId);

    /**
     * 로그인에 필요한 정보만 조회하기 위한 기능
     *
     * @param memberId
     * @return memberId, loginId, password, memberRoll
     */
    @Override
    Optional<MemberForm> findAuthorizationInfoByMemberId(Long memberId);
}
