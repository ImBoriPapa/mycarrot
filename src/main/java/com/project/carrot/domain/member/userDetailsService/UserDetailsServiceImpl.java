package com.project.carrot.domain.member.userDetailsService;

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

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * @param loginId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(()-> new UsernameNotFoundException(loginId));

        return createUserDetails(findMember);
    }

    private UserDetails createUserDetails(Member member){
        List<SimpleGrantedAuthority> grantedAuthority = Collections.singletonList(new SimpleGrantedAuthority("USER_ROLL"));
        return new User(member.getLoginId(), member.getPassword(),grantedAuthority);
    }


}
