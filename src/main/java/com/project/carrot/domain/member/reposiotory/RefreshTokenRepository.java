package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * RefreshTokenRepository
 * Refresh Token 저장
 * findByMemberId() : loginId 로 회원정보 조회
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    Optional<RefreshToken> findByMemberId(Long memberId);
}
