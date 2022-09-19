package com.project.carrot.domain.test.reposotory;

import com.project.carrot.domain.test.testEntity.Member_uuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberUuidRepository extends JpaRepository<Member_uuid, UUID> {
}
