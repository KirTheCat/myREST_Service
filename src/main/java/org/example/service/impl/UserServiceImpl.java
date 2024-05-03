package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User readAll(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public List<User> readByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
