package com.example.desginpattern.structural_patterns.adapter;

import com.example.desginpattern.structural_patterns.adapter.security.UserDetails;
import com.example.desginpattern.structural_patterns.adapter.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {
    private AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        return new AccountUserDetails(accountService.findAccountByUsername(username));
    }
}
