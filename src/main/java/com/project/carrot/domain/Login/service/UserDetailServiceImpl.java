package com.project.carrot.domain.Login.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
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

import static com.project.carrot.exception.errorCode.ErrorCode.NO_RESULT_FIND_MEMBER;
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * @param loginId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException(loginId + NO_RESULT_FIND_MEMBER.getMessage()));
        return createUserDetails(findMember);
    }

    /**
     * @param member required: loginId,password,memberRoll
     * @return UserDetails
     */
    private UserDetails createUserDetails(Member member) {
        List<SimpleGrantedAuthority> grantedAuthority = member.getRoll().stream()
                .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        return new User(String.valueOf(member.getMemberId()), member.getPassword(), grantedAuthority);
    }
}
