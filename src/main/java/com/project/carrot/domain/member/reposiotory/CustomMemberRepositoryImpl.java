package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


public class CustomMemberRepositoryImpl implements CustomMemberRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Member>  findIdAndPwForLoginByLoginId(String LoginId) {

        List<Member> member = em.createQuery("SELECT m.memberId,m.password FROM Member m where m.loginId = :loginId")
                .setParameter("loginId",LoginId)
                .getResultList();
        return member.stream().findAny();
    }
}
