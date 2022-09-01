package com.project.carrot.domain.member.reposiotory;

import com.project.carrot.domain.member.entity.Member;

import java.util.Optional;

public interface CustomMemberRepository {
    /**
     * 로그인을 위한 아이디와 패스워드만 조회 쿼리
     * 현재는 클래스로더 이슈 때문에 사용 x mvc 개발이 끝난 후 Devtools 를 삭제 후 사용 예정
     * @param LoginId
     * @return
     */
    Optional<Member> findIdAndPwForLoginByLoginId(String LoginId);

}
