package com.hanghae99.cloneproject5.security.memberDetail;

import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberDetailsServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public MemberDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        System.out.println("userDetailsService username : " + member.getUsername());

        return new MemberDetailsImpl(member);
    }
}