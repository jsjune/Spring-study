package com.example.desginpattern.structural_patterns.adapter.security;

public interface UserDetailsService {
    UserDetails loadUser(String username);
}
