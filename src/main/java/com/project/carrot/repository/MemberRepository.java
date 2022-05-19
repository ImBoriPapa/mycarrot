package com.project.carrot.repository;

import com.project.carrot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public List<Member> findByMemberIdAndMemberPassword(String memberId, String memberPw);
}
