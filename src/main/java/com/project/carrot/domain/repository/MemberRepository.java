package com.project.carrot.domain.repository;

import com.project.carrot.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByUserId(String userId); //회원 아이디로 검색하기

    Member save(Member member);





}
