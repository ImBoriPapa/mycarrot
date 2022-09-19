package com.project.carrot.domain.Login.service;

import com.project.carrot.domain.member.dto.MemberForm;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.customEx.FailToLoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.carrot.exception.errorCode.ErrorCode.FAIL_TO_LOGIN;
import static com.project.carrot.exception.errorCode.ErrorCode.NO_RESULT_FIND_MEMBER;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * @param memberId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String memberId) {
        MemberForm findMember = memberRepository.findAuthorizationInfoByMemberId(Long.valueOf(memberId))
                .orElseThrow(() -> new UsernameNotFoundException(memberId + NO_RESULT_FIND_MEMBER.getMessage()));
        return createUserDetails(findMember);
    }

    public UserDetails loadUser(String loginId){
        MemberForm findMember = memberRepository.findAuthorizationInfoByLoginId(loginId)
                .orElseThrow(()->new FailToLoginException(FAIL_TO_LOGIN));
        return createUserDetails(findMember);
    }

    /**
     * @param form required: loginId,password,memberRoll
     * @return UserDetails
     */
    private UserDetails createUserDetails(MemberForm form) {
        List<SimpleGrantedAuthority> grantedAuthority = form.getRoll().stream()
                .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        return new User(String.valueOf(form.getMemberId()), form.getPassword(), grantedAuthority);
    }
}
