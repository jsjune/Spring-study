package com.example.desginpattern.structural_patterns.adapter;

import com.example.desginpattern.structural_patterns.adapter.security.LoginHandler;
import com.example.desginpattern.structural_patterns.adapter.security.UserDetailsService;

public class App {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        String login = loginHandler.login("keesun", "keesun");
        System.out.println(login);
    }
}
