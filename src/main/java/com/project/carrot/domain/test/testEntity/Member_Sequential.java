package com.project.carrot.domain.test.testEntity;

import com.fasterxml.uuid.Generators;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member_Sequential {

    @Id
    private UUID sId;
    private String loginId;

    @Builder()
    public Member_Sequential(Long id, String clientId, String loginId, String password) {
        createId();
        this.loginId = loginId;
    }
    public void createId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        this.sId = uuid;
    }
}
