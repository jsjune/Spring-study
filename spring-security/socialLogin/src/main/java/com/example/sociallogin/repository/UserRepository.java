package com.example.sociallogin.repository;

import com.example.sociallogin.model.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private Map<String, Object> users = new HashMap<String, Object>();

    public User findByUsername(String username) {
        if (users.containsKey(username)) {
            return (User) users.get(username);
        }
        return null;
    }

    public void register(User user) {
        if (users.containsKey(user.getUsername())) {
            return;
        }
        users.put(user.getUsername(), user);
    }
}