package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.dto.MemberForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<MemberForm> findAuthorizationInfoByLoginId(String LoginId) {

        List<MemberForm> forms = em.createQuery("SELECT new com.project.carrot.domain.member.dto.MemberForm(m.memberId,m.loginId,m.password,m.memberRoll)  FROM Member m where m.loginId = :loginId")
                .setParameter("loginId", LoginId)
                .getResultList();
        return forms.stream().findAny();
    }

    @Override
    public Optional<MemberForm> findAuthorizationInfoByMemberId(Long memberId) {
        List<MemberForm> forms = em.createQuery("SELECT new com.project.carrot.domain.member.dto.MemberForm(m.memberId,m.loginId,m.password,m.memberRoll)  FROM Member m where m.memberId = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
        return forms.stream().findAny();
    }
}
