package com.example.designpattern.structural_patterns.adapter.security;

public interface UserDetailsService {
    UserDetails loadUser(String username);
}
