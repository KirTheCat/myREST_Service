package org.example.service;

import org.example.model.entity.User;

import java.util.List;

public interface UserService extends Service<User> {

    List<User> readByEmail(String email);
}
