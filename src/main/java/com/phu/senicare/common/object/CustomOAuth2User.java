package com.phu.senicare.common.object;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

// 사용자 지정 OAuth2 유저 객체
public class CustomOAuth2User implements OAuth2User {

    private String name;
    private  Map<String, Object> attributes;
    Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2User(String name, Map<String, Object> attributes) {
        this.name = name;
        this.attributes = attributes;
        this.authorities = AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.name;

    }
    
}
