package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByLoginId(String loginId); //회원 아이디로 검색하기

    Optional<Member> findByEmail(String email); //회원 이메일로 검색하기

    Optional<Member> findByNickname(String nickname);//회원 닉네임으로 검색하기

    Optional<Member> findByMemberId(Long memberId);// 단건 조회

    List<Member> findAll();//전체 회원 조회








}
