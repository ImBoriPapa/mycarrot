package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface MemberRepository extends JpaRepository<Member,Long>,CustomMemberRepository {

    Optional<Member> findByLoginId(String loginId); //회원 아이디로 검색하기
    Optional<Member> findByMemberId(Long memberId);// 단건 조회

    List<Member> findAll();//전체 회원 조회
    boolean existsByLoginId(String loginId);

    boolean existsByNickname(String loginId);

    boolean existsByEmail(String loginId);

    /**
     * 로그인에 필요한 정보만 조회하기 위한 기능
     * 현재는 클래스로더 이슈 때문에 사용 x mvc 개발이 끝난 후 Devtools 를 삭제 후 사용 예정
     * @param LoginId
     * @return loginId,password
     */
    @Override
    Optional<Member> findIdAndPwForLoginByLoginId(String LoginId);

    /**
     * 로그인에 필요한 정보만 조회하기 위한 기능
     * @param loginId
     * @return loginId,password
     */
    @Query("select m from Member m where m.loginId = :loginId")
    Optional<Member> findIdAndPw(@Param("loginId") String loginId);
}
