package com.project.carrot.domain.test.testEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Member_uuid {

    @Id
    private UUID uuid;
    private String loginId;
    @Builder
    public Member_uuid(String loginId) {
        createID();
        this.loginId = loginId;
    }

    public void createID(){
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
    }
}
