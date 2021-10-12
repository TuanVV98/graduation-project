package com.spring.model.security;

import com.spring.model.Accounts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUserFactory {

    public static JwtUserDetailsImpl create(Accounts user) {
        return new JwtUserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), createGrantedAuthorities(user.getRoles().getName()));
    }

    private static List<GrantedAuthority> createGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}
