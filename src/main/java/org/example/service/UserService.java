package org.example.service;

import org.example.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends Service<User> {

    public User getByUsername(String username);
    public UserDetailsService userDetailsService();
    public User getCurrentUser();
}
