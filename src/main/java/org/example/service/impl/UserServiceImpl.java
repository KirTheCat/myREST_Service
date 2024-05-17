package org.example.service.impl;

import java.util.List;

import org.example.model.entity.User;
import org.example.service.UserService;
import org.example.model.enums.RoleEnum;
import org.example.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity){
        return userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User create(User entity) {
        if (userRepository.existsByUsername(entity.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(entity.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(entity);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(RoleEnum.ROLE_ADMIN);
        save(user);
    }
}

