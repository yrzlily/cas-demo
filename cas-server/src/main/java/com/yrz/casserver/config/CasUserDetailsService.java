package com.yrz.casserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author yrz
 */
public class CasUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    private static final Logger log = LoggerFactory.getLogger(CasUserDetailsService.class);

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        String username = token.getName();
        log.debug("current username [{}]", username);
        // 这里应该查询数据库获取具体的用户信息和权限信息
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username, username, authorities);
    }

}
