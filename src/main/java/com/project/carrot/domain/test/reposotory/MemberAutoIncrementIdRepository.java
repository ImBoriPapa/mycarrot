package com.project.carrot.domain.test.reposotory;


import com.project.carrot.domain.test.testEntity.Member_Increment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAutoIncrementIdRepository extends JpaRepository<Member_Increment,Long> {
}
