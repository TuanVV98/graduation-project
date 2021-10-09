package com.spring.service.security.impl;

import com.spring.model.Accounts;
import com.spring.model.security.JwtUserFactory;
import com.spring.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Accounts> account = this.accountService.checkIfEmailExistsAndDeletedAt(email);

        if(account.isPresent()){
            return JwtUserFactory.create(account.get());
        }
        throw new UsernameNotFoundException("User/Email not found.");
    }
}
