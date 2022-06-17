package com.project.carrot.repository;

import com.project.carrot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByMemberId(String memberId); //회원 아이디로 검색하기

    Member save(Member member);



}
