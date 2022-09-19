package com.project.carrot.domain.test.reposotory;

import com.project.carrot.domain.test.testEntity.Member_Sequential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberSequentialUuidRepository extends JpaRepository<Member_Sequential, UUID> {
}
