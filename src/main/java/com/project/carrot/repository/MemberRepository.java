package com.project.carrot.repository;

import com.project.carrot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByMemberId(String memberId); //회원 아이디로 검색하기

    Member save(Member member);
}
