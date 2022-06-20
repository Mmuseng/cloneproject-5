package com.hanghae99.cloneproject5.security;

import com.hanghae99.cloneproject5.model.Member;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class MemberAuthentication extends UsernamePasswordAuthenticationToken {

    public MemberAuthentication(String principal, String credentials) {
        super(principal, credentials);
    }

    public MemberAuthentication(String principal, String credentials,
                              List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
